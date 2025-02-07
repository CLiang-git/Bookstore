package com.example.mapreduce;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class KeywordCount {

    private static final HashSet<String> givenKeywords = new HashSet<String>() {
        {
            this.add("c++");
            this.add("java");
            this.add("go");
            this.add("programming");
            this.add("family");
            this.add("science");
            this.add("love");
            this.add("mystery");
            this.add("history");
        }
    };

    public KeywordCount() {
    }

    // 清除output文件夹中原有文件
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for(int i = 0; i < ((String[]) Objects.requireNonNull(children)).length; ++i) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static void main(String[] args) throws Exception {
        String[] inputs = new String[]{"src/main/resources/data/CS.txt", "src/main/resources/data/Fiction.txt", "src/main/resources/data/Horror.txt", "src/main/resources/data/Mystery.txt"};
        String output = "src/main/resources/output";
        deleteDir(new File(output));
        Date start = new Date();
        Configuration conf = new Configuration();
        conf.set("dfs.defaultFS", "hdfs://hadoop:9000");
        Job job = Job.getInstance(conf, "keyword count");
        job.setJarByClass(KeywordCount.class);
        job.setMapperClass(KeywordCount.TokenizerMapper.class);
        job.setCombinerClass(KeywordCount.IntSumReducer.class); //combiner和reducer的逻辑是一样的，故使用相同的类
        job.setReducerClass(KeywordCount.IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        for (String input : inputs) {
            FileInputFormat.addInputPath(job, new Path(input));
        }

        FileOutputFormat.setOutputPath(job, new Path(output));
        boolean ret = job.waitForCompletion(true);
        Date end = new Date();
        PrintStream printout = System.out;
        double time = (double)(end.getTime() - start.getTime());
        printout.println("Take " + time / 1000.0D + " s in total.");
        System.exit(ret ? 0 : 1);
    }

    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
        private static final IntWritable one = new IntWritable(1);
        private final Text word = new Text();

        public TokenizerMapper() {
        }

        public void map(Object key, Text value, Mapper<Object, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());

            while(itr.hasMoreTokens()) {
                // 把token转换成小写、无前后空格的形式，便于判断是否在给定的关键词列表中
                String token = itr.nextToken().trim().toLowerCase();

                // 处理标点符号粘连在单词前后带来的问题，如ABC, "ABC
                if (token.startsWith("(") || token.startsWith("\"") || token.startsWith("'")) {
                    token = token.substring(1);
                }
                if (token.endsWith(")") || token.endsWith(",") || token.endsWith("\"") || token.endsWith("'")) {
                    token = token.substring(0, token.length() - 1);
                }

                // 判断当前token是否位于所给定的关键词列表当中。若是，则添加到context中去
                if (KeywordCount.givenKeywords.contains(token)) {
                    this.word.set(token);
                    context.write(this.word, one);
                }
            }

        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private final IntWritable result = new IntWritable();

        public IntSumReducer() {
        }

        // 和wordcount一样，直接求和
        public void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            this.result.set(sum);
            context.write(key, this.result);
        }
    }

}

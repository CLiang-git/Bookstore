package com.bookstore.lucene;

import com.alibaba.fastjson.JSONObject;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class Indexer {
    public static final String INDEX_DIR = "./src/main/resources/index/";
    public static final String DATA_DIR = "./src/main/resources/bookInfo/";

    private static void index(String indexPath, String filePath) throws IOException {
        Directory indexDir = FSDirectory.open(Paths.get(indexPath));
        Analyzer analyzer = new StandardAnalyzer();

        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setUseCompoundFile(false);

        IndexWriter writer = new IndexWriter(indexDir, indexWriterConfig);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Read line: " + line);
            JSONObject jsonObj = JSONObject.parseObject(line);
            Document document = new Document();
            document.add(new Field(SearchFields.Details, jsonObj.getString(SearchFields.Details), TextField.TYPE_STORED));
            document.add(new Field(SearchFields.BookId, jsonObj.getString(SearchFields.BookId), StringField.TYPE_STORED));
            writer.addDocument(document);
        }
        reader.close();
        writer.close();
    }

    // create index
    public static void main(String[] args) {
        try {
            index(INDEX_DIR, DATA_DIR + "bookInfo.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
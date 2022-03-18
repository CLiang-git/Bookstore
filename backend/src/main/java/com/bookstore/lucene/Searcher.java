package com.bookstore.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Searcher {

    public static List<Integer> searchBooksBy(String keyword) {
        List<Integer> bookIdList = new ArrayList<>();
        try {
            FSDirectory indexDir = FSDirectory.open(Paths.get(Indexer.INDEX_DIR));
            IndexReader reader = DirectoryReader.open(indexDir);
            IndexSearcher searcher = new IndexSearcher(reader);

            Term term = new Term(SearchFields.Details, keyword);
            Query query = new TermQuery(term);
            TopDocs topDocs = searcher.search(query,15);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                Document document = reader.document(scoreDoc.doc);
                bookIdList.add(Integer.valueOf(document.get(SearchFields.BookId)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookIdList;
    }

    // search with keyword for test
    public static void main(String[] args) throws IOException {
        System.out.println("Input keyword to search:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = reader.readLine()) != null) {
            List<Integer> bookIdList = searchBooksBy(line);
            System.out.println("Result bookIdList: "+ bookIdList);
        }
    }
}

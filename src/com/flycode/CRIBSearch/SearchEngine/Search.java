package com.flycode.CRIBSearch.SearchEngine;

import com.flycode.CRIBSearch.Dialogs.myDialog;
import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.util.Scanner;

/**
 * Used for all search purposes. The Search is used to create an interface
 * for working with lucene (a java-based implementation of a full-text search engine)*/
public class Search {
    private PadSqlUtil padsql = null;
    private String index = "index";

    /**
     * Basic testing function to see if searching is possible
     * */
    public void doTest(){
        try {
            Analyzer analyzer = new StandardAnalyzer();
            // Store the index in memory:
            Directory directory = new RAMDirectory();
            // To store an index on disk, use this instead:
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);

            //Create and add document
            Document doc = new Document();
            doc.add(new TextField("test","This is a test, type test to see functionality",null));
            iwriter.addDocument(doc);
            iwriter.close();

            // Now Search the index:
            DirectoryReader ireader = DirectoryReader.open(directory);
            IndexSearcher isearcher = new IndexSearcher(ireader);

            // Parse a simple query that searches for "test":
            QueryParser parser = new QueryParser("fieldname", analyzer);
            System.out.println("Enter search term (test): ");
            Scanner in = new Scanner(System.in);
            String queryTerm = in.next();
            Query query = parser.parse("test");

            //TODO: check on Search test code.
            ScoreDoc[] hits = isearcher.search(query, 1000, null).scoreDocs;

            // Iterate through the results:
            for (ScoreDoc hit : hits) {
                Document hitDoc = isearcher.doc(hit.doc);
                boolean truth = hitDoc.get("1").equals("1");
                if (truth) {
                    myDialog.showInfo("Success", "SearchEngine is functional");
                }
            }
            ireader.close();
            directory.close();
        }catch (Exception e){
            e.printStackTrace();
            myDialog.showError("Error","Something is wrong with the SearchEngine");
        }
    }

   /* public void DBaccess(PadSqlUtil padsql){
        this.padsql = padsql;
    }*/

    /*public void SearchAll(String[] args){
        try{
        Analyzer analyzer = new StandardAnalyzer();
        // Store the index in memory:
        Directory directory = new RAMDirectory();
        // To store an index on disk, use this instead:
        //Directory directory = FSDirectory.open("/tmp/testindex");
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);

        Document doc = new SearchDocument()
                .Build(padsql)
                .addBuildingDoc()
                .addOwnerDoc()
                .addTenantDoc()
                .generateDoc();
        iwriter.addDocument(doc);
        iwriter.close();

        // Now Search the index:
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("fieldname", analyzer);
        Query query = parser.parse("text");

        //TODO: check on Search test code.
        ScoreDoc[] hits = isearcher.search(query, 1000, null).scoreDocs;

        // Iterate through the results:
        for (ScoreDoc hit : hits) {
            Document hitDoc = isearcher.doc(hit.doc);
            boolean truth = hitDoc.get("1").equals("1");
            if (truth) {
                myDialog.showInfo("Success", "SearchEngine is functional");
            }
        }
        ireader.close();
        directory.close();
    }catch (Exception e){
        e.printStackTrace();
        myDialog.showError("Error","Something is wrong with the SearchEngine");
    }
    }*/

}


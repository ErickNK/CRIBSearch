package com.flycode.CRIBSearch.SearchEngine;

import com.flycode.CRIBSearch.Entities.Building;
import com.flycode.CRIBSearch.Entities.Owner;
import com.flycode.CRIBSearch.Entities.Tenant;
import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import com.flycode.CRIBSearch.interfaces.DBdependent;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;

/**
 * This class is used primarily for indexing the whole database<br/>
 * or specific tables in the database.*/
public class IndexDB implements DBdependent{
    private static PadSqlUtil padsql;

    public IndexDB(PadSqlUtil padsql){
        DBaccess(padsql);
    }

    @Override
    public void DBaccess(PadSqlUtil p) {
        padsql = p;
    }

    private static class Build{
        String indexPath = "index";
        IndexWriter writer;
        private Build create(){
            //TODO: check if the index directory and index files exist
            try {
                System.out.println("Indexing to directory '" + indexPath + "'...");
                Directory dir = FSDirectory.open(Paths.get(indexPath));
                Analyzer analyzer = new StandardAnalyzer();
                IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

                // Create a new index in the directory, removing any
                // previously indexed documents:
                iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

                // Optional: for better indexing performance, if you
                // are indexing many documents, increase the RAM
                // buffer.  But if you do this, increase the max heap
                // size to the JVM (eg add -Xmx512m or -Xmx1g):
                //
                // iwc.setRAMBufferSizeMB(256.0);

                writer = new IndexWriter(dir, iwc);

                // NOTE: if you want to maximize search performance,
                // you can optionally call forceMerge here.  This can be
                // a terribly costly operation, so generally it's only
                // worth it when your index is relatively static (ie
                // you're done adding documents to it):
                //
                // writer.forceMerge(1);

            } catch (IOException e) {
                System.out.println(" caught a " + e.getClass() +
                        "\n with message: " + e.getMessage());
            }
            return this;
        }
        private Build indexWholeDatabase(PadSqlUtil p) throws Exception{
            Date start = new Date();
            if (writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE) {
                // New index, so we just add the document (no old document can be there):
                System.out.println("Indexing database...");
                SearchDocument sd = new SearchDocument(p,writer);
                sd.addOwnerDocs();
                sd.addTenantDocs();
                sd.addBuildingDocs();
            } else {
                // Existing index (an old copy of this document may have been indexed) so
                // we use updateDocument instead to replace the old one matching the exact
                // path, if present:
                System.out.println("Index all ready exists");
                //w.updateDocument(new Term("path", file.toString()), doc);
            }
            writer.close();
            Date end = new Date();
            System.out.println(end.getTime() - start.getTime() + " total milliseconds");
            return this;
        }
        private Build indexDoc(Document doc) throws Exception{
            writer.addDocument(doc);
            return this;
        }
        private Build updateDoc(Document doc,String FieldName,String text) throws Exception{
            writer.updateDocument(new Term(FieldName,text),doc);
            return this;
        }
    }

    public static void addTenantDoc(Tenant data){
        try {
            Document doc = new Document();
            doc.add(new StoredField("id",data.getId()));
            doc.add(new TextField("firstName",data.getFirst(),null));
            doc.add(new TextField("secondName",data.getSecond(),null));
            doc.add(new TextField("surname",data.getSurname(),null));
            doc.add(new IntPoint("tell", data.getTell()));
            doc.add(new IntPoint("nationalID", data.getNational_ID()));
            doc.add(new TextField("bio", data.getBio(),null));

            new Build()
                    .create()
                    .indexDoc(doc);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateTenantDoc(Tenant data){
        try {
            Document doc = new Document();
            doc.add(new StoredField("id",data.getId()));
            doc.add(new TextField("firstName",data.getFirst(),null));
            doc.add(new TextField("secondName",data.getSecond(),null));
            doc.add(new TextField("surname",data.getSurname(),null));
            doc.add(new IntPoint("tell", data.getTell()));
            doc.add(new IntPoint("nationalID", data.getNational_ID()));
            doc.add(new TextField("bio", data.getBio(),null));
            new Build()
                    .create()
                    .updateDoc(doc,"id", String.valueOf(data.getId()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addBuildingDoc(Building data){
        try {
            Document doc = new Document();
            doc.add(new StoredField("id",data.getId()));
            doc.add(new IntPoint("Registration ID",data.getRegistration_id()));
            doc.add(new TextField("Name",data.getName(),null));
            doc.add(new TextField("OwnerName",data.getOwner_Name(),null));
            doc.add(new StringField("License", data.getLicense(),null));
            doc.add(new TextField("Location",data.getLocation(),null));
            doc.add(new IntPoint("No of rooms", data.getNo_of_rooms()));

            new Build()
                    .create()
                    .indexDoc(doc);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateBuildingDoc(Building data){
        try {
            Document doc = new Document();
            doc.add(new StoredField("id",data.getId()));
            doc.add(new IntPoint("Registration ID",data.getRegistration_id()));
            doc.add(new TextField("Name",data.getName(),null));
            doc.add(new TextField("OwnerName",data.getOwner_Name(),null));
            doc.add(new StringField("License", data.getLicense(),null));
            doc.add(new TextField("Location",data.getLocation(),null));
            doc.add(new IntPoint("No of rooms", data.getNo_of_rooms()));

            new Build()
                    .create()
                    .updateDoc(doc,"id", String.valueOf(data.getId()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addOwnerDoc(Owner data){
        try {
            Document doc = new Document();
            doc.add(new StoredField("id",data.getId()));
            doc.add(new TextField("firstName",data.getFirst(),null));
            doc.add(new TextField("secondName",data.getSecond(),null));
            doc.add(new TextField("surname",data.getSurname(),null));
            doc.add(new IntPoint("tell", data.getTell()));
            doc.add(new IntPoint("nationalID", data.getNational_id()));
            doc.add(new TextField("bio", data.getBio(),null));
            doc.add(new IntPoint("Owner Id",data.getOwner_id()));

            new Build()
                    .create()
                    .indexDoc(doc);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateOwnerDoc(Owner data){
        try {
            Document doc = new Document();
            doc.add(new StoredField("id",data.getId()));
            doc.add(new TextField("firstName",data.getFirst(),null));
            doc.add(new TextField("secondName",data.getSecond(),null));
            doc.add(new TextField("surname",data.getSurname(),null));
            doc.add(new IntPoint("tell", data.getTell()));
            doc.add(new IntPoint("nationalID", data.getNational_id()));
            doc.add(new TextField("bio", data.getBio(),null));
            doc.add(new IntPoint("Owner Id",data.getOwner_id()));

            new Build()
                    .create()
                    .updateDoc(doc,"id", String.valueOf(data.getId()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void IndexWholeDB(){
        try {
            new Build()
                    .create()
                    .indexWholeDatabase(padsql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

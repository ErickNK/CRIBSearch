package com.flycode.CRIBSearch.SearchEngine;


import com.flycode.CRIBSearch.PadSql.PadSqlUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;

import java.sql.ResultSet;

//TODO: improve on SearchDocument class
public class SearchDocument {
    private PadSqlUtil p;
    private IndexWriter w;

    public SearchDocument(PadSqlUtil p,IndexWriter w){
        this.p = p;
        this.w = w;
    }

    public void addBuildingDocs(){
        try {
            ResultSet rs = p.selectTable("building");
            while(rs.next()){
                Document doc = new Document();
                doc.add(new StoredField("id",rs.getString(1)));
                doc.add(new TextField("name",rs.getString(2),null));
                doc.add(new TextField("ownerName",rs.getString(3),null));
                doc.add(new StringField("license",rs.getString(4),null));
                doc.add(new TextField("location",rs.getString(5),null));
                doc.add(new IntPoint("NoOfRooms", Integer.parseInt(rs.getString(6))));
                w.addDocument(doc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addOwnerDocs(){
        try {
            ResultSet rs = p.selectTable("owner");
            while (rs.next()) {
                Document doc = new Document();
                doc.add(new StoredField("id",rs.getString(1)));
                doc.add(new TextField("firstName",rs.getString(2),null));
                doc.add(new TextField("secondName",rs.getString(3),null));
                doc.add(new TextField("surname",rs.getString(4),null));
                doc.add(new IntPoint("nationalID", Integer.parseInt(rs.getString(5))));
                doc.add(new TextField("bio",rs.getString(6),null));
                doc.add(new IntPoint("tell", Integer.parseInt(rs.getString(7))));
                doc.add(new IntPoint("ownerID", Integer.parseInt(rs.getString(8))));
                w.addDocument(doc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addTenantDocs(){
        try {
            ResultSet rs = p.selectTable("tenant");
            while (rs.next()) {
                Document doc = new Document();
                doc.add(new StoredField("id",rs.getString(1)));
                doc.add(new TextField("firstName",rs.getString(2),null));
                doc.add(new TextField("secondName",rs.getString(3),null));
                doc.add(new TextField("surname",rs.getString(4),null));
                doc.add(new IntPoint("tell", Integer.parseInt(rs.getString(5))));
                doc.add(new IntPoint("nationalID", Integer.parseInt(rs.getString(6))));
                doc.add(new TextField("bio", rs.getString(7),null));
                w.addDocument(doc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

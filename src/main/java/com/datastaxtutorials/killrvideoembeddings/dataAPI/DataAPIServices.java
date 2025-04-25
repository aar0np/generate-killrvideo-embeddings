package com.datastaxtutorials.killrvideoembeddings.dataAPI;

import java.util.ArrayList;
import java.util.List;

import com.datastax.astra.client.DataAPIClient;
import com.datastax.astra.client.core.options.DataAPIClientOptions;
import com.datastax.astra.client.databases.Database;
import com.datastax.astra.client.tables.Table;
import com.datastaxtutorials.killrvideoembeddings.entities.VideoTableEntity;

public class DataAPIServices {

    private String astraToken;
    private String astraDbApiEndpoint;
    private String astraDbKeyspace;

    private Database database;
    private Table<VideoTableEntity> videosRepository;
    
    public DataAPIServices() {
    	astraToken = System.getenv("ASTRA_DB_APP_TOKEN");
    	astraDbApiEndpoint = System.getenv("ASTRA_DB_ENDPOINT");
    	astraDbKeyspace = System.getenv("ASTRA_DB_KEYSPACE");
    	
    	DataAPIClientOptions options = new DataAPIClientOptions().logRequests();
        database = getDatabase(new DataAPIClient(astraToken, options));
        this.videosRepository = database.getTable(VideoTableEntity.class);
    }
    
    private Database getDatabase(DataAPIClient client) {
        return client.getDatabase(astraDbApiEndpoint, astraDbKeyspace);
    }
    
    public List<VideoTableEntity> getAllVideos() {

    	List<VideoTableEntity> videos = new ArrayList<>();
		try {
			videosRepository.findAll().forEach(videos::add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return videos;
	}
    
    public void saveVideo(VideoTableEntity video) {
		try {
			videosRepository.insertOne(video);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

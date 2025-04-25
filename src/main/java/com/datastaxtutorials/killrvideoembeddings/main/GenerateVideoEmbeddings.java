package com.datastaxtutorials.killrvideoembeddings.main;

import java.util.List;
import java.util.UUID;

import com.datastax.astra.client.core.vector.DataAPIVector;
import com.datastaxtutorials.killrvideoembeddings.dataAPI.DataAPIServices;
import com.datastaxtutorials.killrvideoembeddings.entities.VideoTableEntity;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;

public class GenerateVideoEmbeddings {
	
	private static EmbeddingModel embeddingModel;
	
	public static void main(String[] args) {

		// initialize the embedding model
		embeddingModel = new AllMiniLmL6V2EmbeddingModel();
		
		// initialize the Data API services
		DataAPIServices dataAPIServices = new DataAPIServices();
		
		// generate embeddings for all videos
		List<VideoTableEntity> allVideos = dataAPIServices.getAllVideos();
		
		for (VideoTableEntity video : allVideos) {
			UUID videoId = video.getVideoId();
			String title = video.getName();
			String description = video.getDescription();
			
			// generate embedding for the title and description
			float[] videoEmbedding = embeddingModel.embed(title + " " + description)
					.content().vector();
			
			VideoTableEntity videoWithEmbedding = new VideoTableEntity();
			videoWithEmbedding.setVideoId(videoId);
			videoWithEmbedding.setVideoVector(new DataAPIVector(videoEmbedding));
			
			dataAPIServices.saveVideo(videoWithEmbedding);
		}
	}

}

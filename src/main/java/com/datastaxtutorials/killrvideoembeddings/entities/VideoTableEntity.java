package com.datastaxtutorials.killrvideoembeddings.entities;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import com.datastax.astra.client.tables.mapping.Column;
import com.datastax.astra.client.tables.mapping.EntityTable;
import com.datastax.astra.client.tables.mapping.PartitionBy;
import com.datastax.astra.client.core.vector.DataAPIVector;

@EntityTable("videos")
public class VideoTableEntity {
	@PartitionBy(0)
	@Column(name="videoid")
	private UUID videoId;
	@Column(name="added_date")
	private Instant addedDate;
	private String description;
	private String location;
	@Column(name="location_type")
	private String locationType;
	private String name;
	@Column(name="preview_image_location")
	private String previewImageLocation;
	@Column(name="solr_query")
	private String solrQuery;
	private Set<String> tags;
	@Column(name="user_id")
	private UUID userId;
    @Column(name="video_vector")
    private DataAPIVector videoVector;
	
	public UUID getVideoId() {
		return videoId;
	}
	public void setVideoId(UUID videoId) {
		this.videoId = videoId;
	}
	public Instant getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Instant addedDate) {
		this.addedDate = addedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreviewImageLocation() {
		return previewImageLocation;
	}
	public void setPreviewImageLocation(String previewImageLocation) {
		this.previewImageLocation = previewImageLocation;
	}
	public String getSolrQuery() {
		return solrQuery;
	}
	public void setSolrQuery(String solrQuery) {
		this.solrQuery = solrQuery;
	}
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	public UUID getUserId() {
		return userId;
	}
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	public DataAPIVector getVideoVector() {
		return videoVector;
	}
	public void setVideoVector(DataAPIVector videoVector) {
		this.videoVector = videoVector;
	}
}

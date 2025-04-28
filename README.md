# generate-killrvideo-embeddings
Reads all videos stored in the `killrvideo.videos` table, and uses HuggingFace's [All-MiniLM-L6-v2](https://huggingface.co/sentence-transformers/all-MiniLM-L6-v2) sentence transformer model to generate vector embeddings based on the video name and description. The 384-dimensional (float) vector embeddings returned are then stored back to the `videos` table's `video_vector` column. 

## Requirements
 - Java 24
 - Access to HuggingFace
 - The following `killrvideo.videos` CQL schema:

```SQL
CREATE TABLE killrvideo.videos (
    videoid uuid PRIMARY KEY,
    added_date timestamp,
    description text,
    location text,
    location_type int,
    name text,
    preview_image_location text,
    tags set<text>,
    userid uuid,
    video_vector vector<float, 384>
);
CREATE CUSTOM INDEX video_vector_idx ON killrvideo.videos (video_vector) USING 'StorageAttachedIndex';
```

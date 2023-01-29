# YoutubeCrawler
API to fetch the latest videos sorted in reverse chronological order of their publishing date-time from YouTube for a given tag/search query in a paginated response.

# Project Goal

To make an API to fetch latest videos sorted in reverse chronological order of their publishing date-time from YouTube for a given tag/search query in a paginated response.

# Basic Requirements:

- Server should call the YouTube API continuously in background (async) with some interval (say 10 seconds) for fetching the latest videos for a predefined search query and should store the data of videos (specifically these fields - Video title, description, publishing datetime, thumbnails URLs and any other fields you require) in a database with proper indexes.
- A GET API which returns the stored video data in a paginated response sorted in descending order of published datetime.
- A basic search API to search the stored videos using their title and description.
- Dockerize the project.
- It should be scalable and optimised.

# Steps to run the project
- From the root directory - /crawler
- Run `mvn clean package`
- Run `docker-compose build`
- Run `docker-compose up`

# Further improvements
- There are a lot of videos that the YouTube v3 API is fetching, we could implement a local cache or put the video id's in redis (with some TTL) and have a check if any ID is present in cache then don't push that to the database.
- The search query could be optimized using elastic search to maintain a reverse-index on the search query.

# Benchmarking
- YouTube API fetches 50 records for every hit. (limit set by youtube)
- For a 10 sec cron, no of write query = 5 * 60 * 60 * 24 = 432K requests/day 
- For a high QPS of write, we could shard the database on timestamp, but we could to a problem hot shard issue i.e for some interval one shard will have a lot of write query triggers, but this could help in optimizing a range query based on time for every hour (i.e 1pm - 2pm in one shard). Let's say if I want to get all videos of title like 'Football' uploaded in last 30 minutes, I'll end up querying only one shard and get my results.
- For a high QPS of read, I could replicate each of my shards (i.e having a master and some slaves), any write will come to the master and will propagated in a batch manner asynchronously to all my slaves, and the reads will happen from the slaves (i.e all the slaves would be eventuall consistent with the master), actually depends on the importance of the query, then we can configure them to read from master (to fetch the latest data).
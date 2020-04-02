use std::error::Error;

use rusoto_core::Region;
use rusoto_s3::{S3, S3Client, ListObjectsRequest};

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    let client = S3Client::new(Region::UsWest1);

    let buckets = client.list_buckets().await?;

    let bucket_name_opt = buckets.buckets
        .as_ref()
        .and_then(|buckets| buckets.get(1))
        .and_then(|bucket| bucket.name.as_ref());

    let bucket_name = match bucket_name_opt {
        Some(name) => name,
        None => panic!("No buckets found")
    };

    let input = ListObjectsRequest{
        bucket: bucket_name.clone(),
        ..ListObjectsRequest::default()
    };
    let objects = client.list_objects(input).await?;

    println!("{:?}", objects.contents);
    Ok(())
}
import software.amazon.awssdk.services.s3.{S3AsyncClient, S3Client}

object Main extends App {
  val client = S3AsyncClient.builder().build()
  val bucketsResponse = client.listBuckets()

  println(bucketsResponse)
}

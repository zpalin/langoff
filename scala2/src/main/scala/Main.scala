import software.amazon.awssdk.services.s3.S3AsyncClient
import software.amazon.awssdk.services.s3.model.ListObjectsRequest

import scala.compat.java8.FutureConverters
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object Main extends App {
  val client = S3AsyncClient.builder().build()
  val bucketsResponse = FutureConverters.toScala(client.listBuckets())

  val futObjs = for {
    buckets <- bucketsResponse
    bucket = if (buckets.buckets().size() > 0)
      buckets.buckets().get(0)
    else
      throw new Error("No buckets exist")

    objReq = ListObjectsRequest
        .builder()
        .bucket(bucket.name())
        .build()

    objectsFuture = client.listObjects(objReq)
    objects <- FutureConverters.toScala(objectsFuture)
  } yield objects


  val objects = Await.result(futObjs, 5.seconds)
  println(objects)
}
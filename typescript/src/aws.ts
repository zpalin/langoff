import { S3 } from 'aws-sdk'

const run = async (): Promise<any> => {
  const client = new S3()

  const bucketsResp = await client.listBuckets().promise()
  if (!bucketsResp.Buckets?.length) {
    console.log('No buckets found')
    return
  }

  const bucketName = bucketsResp.Buckets[0].Name || ''
  const objectsResp = await client.listObjects({ Bucket: bucketName }).promise()

  console.log(objectsResp)
}

run().catch(err => {
  console.error(`Server Error: ${err}`)
  process.kill(1)
})

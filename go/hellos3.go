package main

import (
	"fmt"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/s3"
)

func main() {
	sess := session.Must(session.NewSessionWithOptions(session.Options{
		SharedConfigState: session.SharedConfigEnable,
	}))

	svc := s3.New(sess)

	resp, err := svc.ListBuckets(nil); if err != nil {
		fmt.Printf("Error: %+v\n", err)
		return
	}

	if len(resp.Buckets) == 0 {
		fmt.Printf("Error: No buckets found")
		return
	}
	bucket := resp.Buckets[0]

	input := s3.ListObjectsInput{
		Bucket: bucket.Name,
	}
	objResp, err := svc.ListObjects(&input); if err != nil {
		fmt.Printf("Error: %+v\n", err)
		return
	}

	fmt.Printf("%+v\n", objResp)
}
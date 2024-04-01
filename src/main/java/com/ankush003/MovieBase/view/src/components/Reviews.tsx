"use client";
import { Card, CardContent } from "@/components/ui/card"
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel"
import { useState, useEffect } from "react"

interface Review {
    createdAt: string
    id: number
    rating: number
    review: string
}

const Reviews = () => {
    const user = sessionStorage.getItem('user')
    console.log(user)
    const [reviews, setReviews] = useState<Review[]>([])
    
    useEffect(() => {
        if (user == null || user == undefined) {
            setReviews((prevData) => {
                return []
            })
        }
        if (user) {
            const userJson = JSON.parse(user)
            fetch(`/api/users/login?email=${userJson.email}&password=${userJson.password}`)
                .then((res) => {
                    if (!res.ok) {
                        throw new Error("Failed to login user");
                    }
                    return res.json();
                })
                .then((data) => {
                    sessionStorage.setItem("user", JSON.stringify(data))
                    setReviews((prevData) => {
                        return data.reviews;
                    })
                })
        }
    }, [user]);


  return (
    <Carousel
        opts={{
          align: "start",
        }}
        orientation="vertical"
        className="w-full max-w-xs"
      >
        <CarouselContent className="-mt-1 h-[200px]">
          {reviews.map((rev, index) => (
            <CarouselItem key={rev.id} className="pt-1 md:basis-1/2">
            <div className="p-1">
              <Card>
                <CardContent className="flex items-center justify-center p-6 gap-6">
                    <div className="flex flex-col items-center">
                        <p className="text-2xl font-bold">
                        {rev.rating}
                        </p>
                        <p className="text-sm">Rating</p>
                    </div>
                    {/* <div className="flex flex-col items-center">
                        <p className="text-2xl font-bold">
                        {rev.createdAt}
                        </p>
                        <p className="text-sm ">Date</p>
                    </div> */}
                    <div className="text-xl font-bold">
                      <p>
                        {rev.review}
                      </p>
                    </div>
                </CardContent>
              </Card>
            </div>
          </CarouselItem>
          ))}
        </CarouselContent>
        <CarouselPrevious />
        <CarouselNext />
      </Carousel>
  )
}

export default Reviews
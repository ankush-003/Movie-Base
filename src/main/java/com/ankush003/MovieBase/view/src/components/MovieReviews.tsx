"use client";
import { Card, CardContent } from "@/components/ui/card";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";
import { useState, useEffect } from "react";

interface Review {
  createdAt: string;
  id: number;
  rating: number;
  review: string;
}

interface props {
    reviews: Review[]
}

const MovieReviews = ({reviews}: props) => {
  return (
    <Carousel
      opts={{
        align: "start",
      }}
      orientation="vertical"
      className="w-full max-w-xs"
    >
      <CarouselContent className="mt-1 h-[200px] w-full">
        {reviews.map((rev, index) => (
          <CarouselItem key={rev.id} className="pt-1 md:basis-1/2">
            <div className="p-1 w-full">
              <Card>
                <CardContent className="w-full flex items-center justify-center p-6 gap-6">
                  <div className="flex flex-col items-center">
                    <p className="text-2xl font-bold">{rev.rating}</p>
                    <p className="text-sm">Rating</p>
                  </div>
                  {/* <div className="flex flex-col items-center">
                        <p className="text-2xl font-bold">
                        {rev.createdAt}
                        </p>
                        <p className="text-sm ">Date</p>
                    </div> */}
                  <div className="text-xl font-bold">
                    <p>{rev.review}</p>
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
  );
};

export default MovieReviews;

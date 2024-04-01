"use client";
import { useState, useEffect } from "react";
import { Text } from 'lucide-react';
import Link from "next/link";
import {
  Card,
  CardContent,
  CardHeader,
  CardDescription,
  CardFooter,
  CardTitle,
} from "@/components/ui/card";
import Image from "next/image";
import {
  Carousel,
  CarouselContent,
  CarouselItem,
  CarouselNext,
  CarouselPrevious,
} from "@/components/ui/carousel";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import { Button } from "@/components/ui/button";

import React from "react";

export type Movie = {
  id: number;
  title: string;
  releaseDate: string;
  genre: string;
  posterUrl: string;
  director: string;
  averageRating: number;
  totalReviews: number;
  accumulatedRating: number;
  reviews: any[];
};
// sample Movies
// {
//     "id": 802,
//     "title": "The Dark Knight",
//     "releaseDate": "2008-07-18",
//     "genre": "Action, Crime, Drama",
//     "posterUrl": "https://m.media-amazon.com/images/I/81CLFQwU-WL.jpg",
//     "director": "Christopher Nolan",
//     "totalReviews": 2,
//     "accumulatedRating": 14.0,
//     "averageRating": 7.0,
//     "reviews": [
//         {
//             "id": 302,
//             "review": "This is a great movie somethings missing",
//             "rating": 8.0,
//             "createdAt": "2024-04-01T02:27:35.716281Z"
//         },
//         {
//             "id": 303,
//             "review": "This is a great movie somethings missing",
//             "rating": 6.0,
//             "createdAt": "2024-04-01T02:28:06.253248Z"
//         }
//     ]
// }

const Movies = () => {
  let [movies, setMovies] = useState<Movie[]>([]);
  useEffect(() => {
    fetch("/api/movies/all")
      .then((res) => res.json())
      .then((data) => {
        setMovies((prevData) => {
          return [...data];
        });
        console.table(data);
      });
  }, []);
  return (
    <Carousel
      opts={{
        align: "start",
      }}
      className="w-full max-w-screen-xl mx-auto px-4 sm:px-6 lg:px-8"
    >
      <CarouselContent>
        {movies.map((movie, index) => (
          <CarouselItem key={movie.id} className="md:basis-1/2 xl:basis-1/4">
            <div className="p-2 w-[300px] h-3xl">
              <Card className="backdrop-blur-sm">
                <CardHeader>
                  <CardTitle>
                    <div className="text-pretty">{movie.title}</div>
                  </CardTitle>
                  <CardDescription>{movie.releaseDate}</CardDescription>
                </CardHeader>
                <CardContent>
                  <Image
                    src={movie.posterUrl}
                    alt={movie.title}
                    width={400}
                    height={400}
                    className="object-cover w-full h-48 rounded-lg"
                  />
                </CardContent>
                <CardFooter>
                  <div className="flex flex-col w-full">
                    <div className="text-sm text-gray-500">
                      <p>
                        <strong>Director</strong>: {movie.director}
                      </p>
                      <p>
                        <strong>Genre</strong>: {movie.genre}
                      </p>
                      <p>
                        <strong>Average Rating</strong>: {movie.averageRating}
                      </p>
                      <p>
                        <strong>Total Reviews</strong>: {movie.totalReviews}
                      </p>
                    </div>
                    <Link href={`/movie/${movie.id}`}>
                    <Button className="w-full mt-2" variant={"ghost"}>
                        <Text className="w-4 h-4 mx-2" />
                        Details
                    </Button>
                    </Link>
                    
                    {/* <div className="flex gap-2">
                      <Popover>
                        <PopoverTrigger>Review</PopoverTrigger>
                        <PopoverContent>
                          <div className="flex flex-col gap-2">
                            {movie.reviews.map((review, index) => (
                              <div key={index}>
                                <p>{review.review}</p>
                                <p>{review.rating}</p>
                              </div>
                            ))}
                          </div>
                        </PopoverContent>
                      </Popover>
                    </div> */}
                  </div>
                  
                </CardFooter>
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

export default Movies;

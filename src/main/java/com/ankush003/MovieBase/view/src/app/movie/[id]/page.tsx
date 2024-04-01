"use client";
import { type Movie } from "@/components/Movies";
import { useState, useEffect } from "react";
import Image from "next/image";

export default function MoviePage({
  params,
  searchParams,
}: {
  params: { id: string };
  searchParams: { [key: string]: string | string[] | undefined };
}) {
  let [movie, setMovie] = useState<Movie>();
  useEffect(() => {
    fetch(`/api/movies/${params.id}`)
      .then((res) => res.json())
      .then((data) => setMovie(data));
  }, [params.id]);

  console.table(movie);

  return (
    <div className="flex w-full items-center justify-center mt-3">
      <div className="w-full px-10 mx-auto mt-6">
        <div className="flex gap-2 items-center w-full">
          {movie?.posterUrl && (
            <Image
              src={movie?.posterUrl}
              alt={movie?.title}
              width={400}
              height={400}
              className="rounded-lg justify-self-start"
            />
          )}
          <div className="flex flex-col justify-center w-full items-center">
            <h1 className="text-2xl font-semibold leading-none tracking-tight mx-auto my-2 text-center p-2">
              {movie?.title}
            </h1>
            <p className="text-sm text-muted-foreground text-center">
              {movie?.releaseDate}
            </p>
            <div className="text-sm text-gray-500 text-center">
              <p>
                <strong>Director</strong>: {movie?.director}
              </p>
              <p>
                <strong>Genre</strong>: {movie?.genre}
              </p>
              <p>
                <strong>Average Rating</strong>: {movie?.averageRating}
              </p>
              <p>
                <strong>Total Reviews</strong>: {movie?.totalReviews}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

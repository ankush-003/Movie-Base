"use client";
import Reviews from "@/components/Reviews";

function page() {
  return (
    <div className="mt-8 pt-2 flex flex-col items-center justify-center gap-8">
        <p className="text-2xl font-semibold leading-none tracking-tight my-4">
          My Reviews
        </p>
        <Reviews />
    </div>
  )
}

export default page
"use client"
import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { cn } from "@/lib/utils";
import { ThemeProvider } from "@/components/theme-provider";
import Navbar from "@/components/Navbar";
import { Separator } from "@/components/ui/separator"
import { Button } from "@/components/ui/button"
import { useState, useEffect } from "react"
import RegisterDialog from "@/components/RegisterDialog"
import LoginDialog from "@/components/LoginDialog";
import { Toaster } from "@/components/ui/sonner"

const inter = Inter({ subsets: ["latin"], variable: "--font-sans" });

// export const metadata: Metadata = {
//   title: "Create Next App",
//   description: "Generated by create next app",
// };

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const [ isLoggedIn, setIsLoggedIn ] = useState(false)
    useEffect(() => {
        const isUserLoggedIn = sessionStorage.getItem('isLoggedIn')
        if(isUserLoggedIn === 'true') {
            setIsLoggedIn(true)
        }
    }, [])

    const handleLogout = () => {
        sessionStorage.setItem('isLoggedIn', 'false')
        setIsLoggedIn(false)
    }

    const handleLogin = () => {
        sessionStorage.setItem('isLoggedIn', 'true')
        setIsLoggedIn(true)
    }
  return (
    <html lang="en">
      <body className={cn("min-h-screen bg-background font-sans")}>
        <ThemeProvider
          attribute="class"
          defaultTheme="dark"
          enableSystem
          disableTransitionOnChange
        >
          <div className="flex items-center justify-between px-10 w-full py-4">
            <p className="text-3xl font-bold text-orange-400">
              MovieBase
            </p>
            <Navbar />

            <div className="flex items-center justify-between gap-2" >
            {
              isLoggedIn ? (
                <Button onClick={handleLogout}>Logout</Button>
              ) : (
                <LoginDialog />
              )
            }

            {
              !isLoggedIn && (
                <RegisterDialog />
              )
            }
            </div>
            
          </div>
          <Separator />
          {children}
          <Toaster position="top-right" richColors />
        </ThemeProvider>
      </body>
    </html>
  );
}

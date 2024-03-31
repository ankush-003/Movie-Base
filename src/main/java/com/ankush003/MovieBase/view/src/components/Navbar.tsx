"use client";

import * as React from "react";
import Link from "next/link";

import { cn } from "@/lib/utils";
// import { Icons } from "@/components/icons"
import {
  NavigationMenu,
  NavigationMenuContent,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  NavigationMenuTrigger,
  navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu";
import { useState, useEffect } from "react";

export function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  useEffect(() => {
    const isUserLoggedIn = sessionStorage.getItem("isLoggedIn");
    if (isUserLoggedIn === "true") {
      setIsLoggedIn(true);
    }
  }, []);

  const handleLogout = () => {
    sessionStorage.setItem("isLoggedIn", "false");
    setIsLoggedIn(false);
  };

  const handleLogin = () => {
    sessionStorage.setItem("isLoggedIn", "true");
    setIsLoggedIn(true);
  };
  return (
    <NavigationMenu>
      <div>
        <NavigationMenuList>
          <NavigationMenuItem>
            {/* <Link href="/" legacyBehavior passHref>
            <NavigationMenuLink className="text-3xl font-bold text-orange-400">
              MovieBase
            </NavigationMenuLink>
          </Link> */}
          </NavigationMenuItem>
          <NavigationMenuItem>
            <Link href="/" legacyBehavior passHref>
              <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                Home
              </NavigationMenuLink>
            </Link>
          </NavigationMenuItem>
          {isLoggedIn && (
            <NavigationMenuItem>
              <Link href="/review" legacyBehavior passHref>
                <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                  Reviews
                </NavigationMenuLink>
              </Link>
            </NavigationMenuItem>
          )}
          <NavigationMenuItem>
            <Link href="https://github.com/ankush-003" legacyBehavior passHref>
              <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                About
              </NavigationMenuLink>
            </Link>
          </NavigationMenuItem>
        </NavigationMenuList>
      </div>
    </NavigationMenu>
  );
}

const ListItem = React.forwardRef<
  React.ElementRef<"a">,
  React.ComponentPropsWithoutRef<"a">
>(({ className, title, children, ...props }, ref) => {
  return (
    <li>
      <NavigationMenuLink asChild>
        <a
          ref={ref}
          className={cn(
            "block select-none space-y-1 rounded-md p-3 leading-none no-underline outline-none transition-colors hover:bg-accent hover:text-accent-foreground focus:bg-accent focus:text-accent-foreground",
            className
          )}
          {...props}
        >
          <div className="text-sm font-medium leading-none">{title}</div>
          <p className="line-clamp-2 text-sm leading-snug text-muted-foreground">
            {children}
          </p>
        </a>
      </NavigationMenuLink>
    </li>
  );
});
ListItem.displayName = "ListItem";

export default Navbar;

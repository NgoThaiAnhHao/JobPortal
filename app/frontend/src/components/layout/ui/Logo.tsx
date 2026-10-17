import { NavLink } from "react-router";
import LogoIcon from "./LogoIcon";

export default function Logo() {
  return (
    <>
        {/* Brand Logo */}
        <NavLink
          to={'/'}
          className="group flex cursor-pointer select-none items-center gap-1"
        >
          <LogoIcon />

          <span className="font-sans italic text-2xl font-bold tracking-tight text-white">
            Job<span className="text-red-01">tex</span>
          </span>
        </NavLink>
    </>
  )
}
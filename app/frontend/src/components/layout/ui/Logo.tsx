import { NavLink } from "react-router";
import LogoIcon from "./LogoIcon";

export default function Logo({ compact = false }: { compact?: boolean }) {
  return (
    <>
        {/* Brand Logo */}
        <NavLink
          to={'/'}
          onClick={() => window.scrollTo({ top: 0, left: 0, behavior: "instant" })}
          className="group flex cursor-pointer select-none items-center gap-1"
        >
          <LogoIcon compact={compact} />

          <span className={`font-sans italic font-bold tracking-tight text-white transition-all duration-300 ${compact ? "text-xl" : "text-2xl"}`}>
            Job<span className="text-red-01">tex</span>
          </span>
        </NavLink>
    </>
  )
}

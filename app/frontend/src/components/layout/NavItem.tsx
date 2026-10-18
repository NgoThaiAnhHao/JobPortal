import { useState } from "react";
import { NavLink } from "react-router";

type SubMenuItem = {
  label: string;
  href: string;
};

type Props = {
  label: string;
  dropdown: boolean;
  active?: boolean;
  submenu?: SubMenuItem[];
};

export default function NavItem({
  label,
  dropdown,
  active,
  submenu = [],
}: Props) {
  const [isOpen, setIsOpen] = useState(false);

  const href =
    label === "Home" ? "/" : `/${label.toLowerCase().replace(/\s+/g, "-")}`;

  return (
    <div
      className="relative"
      onMouseEnter={() => setIsOpen(true)}
      onMouseLeave={() => setIsOpen(false)}
    >
      <NavLink
        to={href}
        onClick={() => {
          if (label === "Home") {
            window.scrollTo({ top: 0, left: 0, behavior: "instant" });
          }
        }}
        className={`flex items-center gap-1.5 transition-colors hover:text-red-01 
            ${isOpen ? "text-red-01" : ""} 
            ${active ? "font-semibold text-red-01" : ""}`}
      >
        {label}
        {dropdown && (
          <i
            className={`fa-solid fa-chevron-down text-[11px] transition-transfor`}
            aria-hidden="true"
          />
        )}
      </NavLink>

      {dropdown && submenu.length > 0 && (
        <div
          className={`absolute left-0 top-full z-50 min-w-48 bg-blue-02 shadow-lg transition-all duration-500 ${
            isOpen
              ? "visible translate-y-0 opacity-100"
              : "invisible -translate-y-2 opacity-0"
          }`}
        >
          {submenu.map((item) => (
            <NavLink
              key={item.href}
              to={item.href}
              className={({ isActive }) =>
                `block px-5 py-3 text-sm transition-colors duration-200 hover:bg-gray-200/30 ${
                  isActive ? "font-semibold text-red-01" : "text-white"
                }`
              }
              onClick={() => setIsOpen(false)}
            >
              {item.label}
            </NavLink>
          ))}
        </div>
      )}
    </div>
  );
}

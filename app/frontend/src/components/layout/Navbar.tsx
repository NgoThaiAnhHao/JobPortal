import { useState } from "react";
import Logo from "./ui/Logo";
import NavItem from "./NavItem";
import { NavLink } from "react-router";

const navigationItems = [
  { label: "Home", dropdown: false, active: true },
  { label: "About", dropdown: false },

  {
    label: "Jobs",
    dropdown: true,
    submenu: [
      { label: "Browse Jobs", href: "/jobs" },
      { label: "Job Categories", href: "/jobs/categories" },
      { label: "Saved Jobs", href: "/jobs/saved" },
      { label: "Applied Jobs", href: "/jobs/applied" },
    ],
  },

  // Companies
  {
    label: "Companies",
    dropdown: true,
    submenu: [
      { label: "Browse Companies", href: "/companies" },
      { label: "My Company", href: "/my-company" },
      { label: "Create Company", href: "/companies/create" },
      { label: "Edit Company", href: "/companies/edit" },
    ],
  },
  { label: "Blog", dropdown: false },
  { label: "Contact Us", dropdown: false },
];

export default function Navbar() {
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [openMobileSubmenu, setOpenMobileSubmenu] = useState<string | null>(
    null,
  );

  return (
    <header className="font-sans sticky top-0 z-50 w-full bg-blue-01 shadow-md">
      <div className="mx-auto flex h-20 max-w-[1440px] items-center justify-between px-6 lg:px-12">
        <Logo />

        {/* Desktop Navigation */}
        <nav
          className="hidden items-center text-[16px] font-medium text-white/90 md:flex lg:gap-8"
          style={{ gap: "3rem" }}
        >
          {navigationItems.map((item) => (
            <NavItem
              key={item.label}
              label={item.label}
              dropdown={item.dropdown}
              active={item.active}
              submenu={item.submenu}
            />
          ))}
        </nav>

        {/* Desktop Actions */}
        <div className="hidden items-center gap-6 md:flex">
          <NavLink
            to="/auth/sign-up"
            className="text-[15px] font-bold text-white/90 transition-colors duration-500 hover:text-red-01"
          >
            Sign Up
          </NavLink>

          <NavLink
            to="/auth/login"
            className="inline-flex font-bold items-center justify-center rounded-md bg-red-01 px-6 py-2.5 text-[15px] text-white shadow-sm transition-all duration-500 hover:bg-[white] hover:text-inherit"
          >
            Sign In
          </NavLink>
        </div>

        {/* Mobile Menu Button */}
        <button
          type="button"
          onClick={() => {
            setIsMobileMenuOpen((prev) => {
              if (prev) setOpenMobileSubmenu(null);
              return !prev;
            });
          }}
          className="rounded p-2 text-white hover:bg-white/10 focus:outline-none md:hidden"
          aria-label={
            isMobileMenuOpen ? "Close navigation menu" : "Open navigation menu"
          }
          aria-expanded={isMobileMenuOpen}
          aria-controls="mobile-menu"
        >
          <i
            className={`fa-solid ${
              isMobileMenuOpen ? "fa-xmark" : "fa-bars"
            } text-xl`}
            aria-hidden="true"
          />
        </button>
      </div>

      {/* Mobile Navigation */}
      <div
        id="mobile-menu"
        className={`border-t border-white/10 bg-[#051329] px-6 py-5 md:hidden ${
          isMobileMenuOpen ? "block" : "hidden"
        }`}
      >
        <nav className="flex flex-col gap-1 text-[15px] font-medium text-white/90">
          {navigationItems.map((item) => (
            <div key={item.label}>
              {item.dropdown && item.submenu?.length ? (
                <>
                  {/* Dropdown parent */}
                  <button
                    type="button"
                    onClick={() =>
                      setOpenMobileSubmenu((prev) =>
                        prev === item.label ? null : item.label,
                      )
                    }
                    className={`flex w-full items-center justify-between rounded-md px-3 py-3 transition-colors hover:bg-white/5 hover:text-red-01 ${
                      openMobileSubmenu === item.label ? "text-red-01" : ""
                    }`}
                    aria-expanded={openMobileSubmenu === item.label}
                  >
                    <span>{item.label}</span>

                    <i
                      className={`fa-solid fa-chevron-down text-xs transition-transform duration-200 ${
                        openMobileSubmenu === item.label ? "rotate-180" : ""
                      }`}
                      aria-hidden="true"
                    />
                  </button>

                  {/* Dropdown submenu */}
                  <div
                    className={`grid transition-[grid-template-rows] duration-300 ${
                      openMobileSubmenu === item.label
                        ? "grid-rows-[1fr]"
                        : "grid-rows-[0fr]"
                    }`}
                  >
                    <div className="overflow-hidden">
                      <div className="ml-3 border-l border-white/15 py-1 pl-3">
                        {item.submenu.map((subItem) => (
                          <NavLink
                            key={subItem.href}
                            to={subItem.href}
                            onClick={() => {
                              setIsMobileMenuOpen(false);
                              setOpenMobileSubmenu(null);
                            }}
                            className={({ isActive }) =>
                              `block rounded-md px-3 py-3 text-sm transition-colors hover:bg-white/10 hover:text-red-01 ${
                                isActive
                                  ? "font-semibold text-red-01"
                                  : "text-white/75"
                              }`
                            }
                          >
                            {subItem.label}
                          </NavLink>
                        ))}
                      </div>
                    </div>
                  </div>
                </>
              ) : (
                <NavLink
                  to={
                    item.label === "Home"
                      ? "/"
                      : `/${item.label.toLowerCase().replace(/\s+/g, "-")}`
                  }
                  onClick={() => {
                    setIsMobileMenuOpen(false);
                    setOpenMobileSubmenu(null);
                  }}
                  className={({ isActive }) =>
                    `block rounded-md px-3 py-3 transition-colors hover:bg-white/5 hover:text-red-01 ${
                      isActive || item.active ? "font-semibold text-red-01" : ""
                    }`
                  }
                >
                  {item.label}
                </NavLink>
              )}
            </div>
          ))}

          {/* Mobile Authentication Actions */}
          <div className="mt-3 flex flex-col gap-3 border-t border-white/10 pt-4">
            <NavLink
              to="/auth/register"
              onClick={() => setIsMobileMenuOpen(false)}
              className="rounded-md py-3 text-center text-white/90 transition-colors hover:bg-white/5 hover:text-red-01"
            >
              Sign Up
            </NavLink>

            <NavLink
              to="/auth/login"
              onClick={() => setIsMobileMenuOpen(false)}
              className="rounded-md bg-red-01 py-3 text-center font-bold text-white transition-colors hover:bg-white hover:text-gray-900"
            >
              Sign In
            </NavLink>
          </div>
        </nav>
      </div>
    </header>
  );
}

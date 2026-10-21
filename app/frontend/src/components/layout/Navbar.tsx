import { useEffect, useRef, useState } from "react";
import Logo from "./ui/Logo";
import NavItem from "./NavItem";
import { Link, NavLink, useNavigate } from "react-router";
import { useToast } from "./ui/toastContext";
import { AUTH_CHANGE_EVENT, clearAuthSession, getAuthSession, type AuthSession } from "../../lib/api/authStorage";
import { getApiErrorMessage } from "../../lib/api/agent";
import { logout } from "../../lib/api/auth";
import { getMyProfileName } from "../../lib/api/profile";
import { saveAuthProfileName } from "../../lib/api/authStorage";

type UserMenuProps = {
  session: AuthSession;
  mobile?: boolean;
  isSigningOut: boolean;
  onSignOut: () => void;
};

function UserMenu({ session, mobile = false, isSigningOut, onSignOut }: UserMenuProps) {
  const [isOpen, setIsOpen] = useState(false);
  const menuRef = useRef<HTMLDivElement>(null);
  const firstName = session.user.firstName?.trim();
  const lastName = session.user.lastName?.trim();
  const hasFullName = Boolean(firstName && lastName);
  const displayName = hasFullName
    ? `${firstName} ${lastName} - ${session.user.email}`
    : session.user.email || "User";
  const initials = hasFullName
    ? `${firstName?.charAt(0)}${lastName?.charAt(0)}`.toUpperCase()
    : session.user.email.trim().charAt(0).toUpperCase() || "U";

  useEffect(() => {
    if (!isOpen) return;
    const closeOnOutsideClick = (event: MouseEvent) => {
      if (event.target instanceof Node && !menuRef.current?.contains(event.target)) setIsOpen(false);
    };
    const closeOnEscape = (event: KeyboardEvent) => {
      if (event.key === "Escape") setIsOpen(false);
    };
    document.addEventListener("mousedown", closeOnOutsideClick);
    document.addEventListener("keydown", closeOnEscape);
    return () => {
      document.removeEventListener("mousedown", closeOnOutsideClick);
      document.removeEventListener("keydown", closeOnEscape);
    };
  }, [isOpen]);

  return (
    <div
      ref={menuRef}
      className={`relative ${mobile ? "w-full md:hidden" : "hidden md:block"}`}
      onMouseEnter={() => setIsOpen(true)}
      onMouseLeave={() => setIsOpen(false)}
    >
      <button
        type="button"
        onClick={() => setIsOpen((open) => mobile ? !open : true)}
        className={`group flex items-center gap-3 rounded-md px-3 py-2 transition-colors focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-white ${mobile ? "w-full" : ""}`}
        aria-label={`Account menu for ${session.user.email || "user"}`}
        aria-haspopup="menu"
        aria-expanded={isOpen}
      >
        <span className="grid h-10 w-10 place-items-center rounded-full border-2 border-white/80 bg-red-01 text-sm font-bold text-white shadow-md">
          {initials}
        </span>
        {mobile && <span className="min-w-0 flex-1 truncate text-left text-sm font-semibold text-white">{session.user.email}</span>}
      </button>

      <div
        className={`absolute right-0 top-full z-50 mt-1 min-w-60 rounded-md bg-blue-02 py-2 shadow-lg transition-all duration-500 ${isOpen ? "visible translate-y-0 opacity-100" : "invisible pointer-events-none -translate-y-2 opacity-0"} ${mobile ? "left-0 right-0" : "w-72"}`}
        role="menu"
        aria-hidden={!isOpen}
      >
          <div className="border-b border-white/10 px-5 pb-3 pt-2 text-white">
            <p className="break-words text-sm font-semibold">{displayName}</p>
            <p className="mt-1 text-xs capitalize text-white/65">{session.user.role.replaceAll("_", " ").toLowerCase()}</p>
          </div>
          <Link
            to="/profile-settings"
            onClick={() => setIsOpen(false)}
            className="group flex items-center gap-3 px-5 py-3 text-sm text-white transition-colors duration-200 hover:bg-gray-200/30 hover:text-white"
            role="menuitem"
          >
            <i className="fa-solid fa-user-gear w-4 text-center transition-colors duration-200 group-hover:text-ưhite" aria-hidden="true" />
            <span>Profile Settings</span>
          </Link>
          <button
            type="button"
            onClick={onSignOut}
            disabled={isSigningOut}
            className="flex w-full cursor-pointer items-center gap-3 px-5 py-3 text-left text-sm font-bold text-red-400 transition-colors duration-200 hover:bg-red-500/15 hover:text-red-400 disabled:cursor-wait disabled:opacity-60"
            role="menuitem"
          >
            <i className="fa-solid fa-right-from-bracket w-4 text-center" aria-hidden="true" />
            {isSigningOut ? "Signing Out..." : "Sign Out"}
          </button>
      </div>
    </div>
  );
}

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
  const navigate = useNavigate();
  const { notify } = useToast();
  const [session, setSession] = useState<AuthSession | null>(() => getAuthSession());
  const [isSigningOut, setIsSigningOut] = useState(false);
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const [openMobileSubmenu, setOpenMobileSubmenu] = useState<string | null>(
    null,
  );
  const accessToken = session?.accessToken;
  const role = session?.user.role;
  const scrollToTop = () => window.scrollTo({ top: 0, left: 0, behavior: "instant" });

  useEffect(() => {
    const syncSession = () => setSession(getAuthSession());
    window.addEventListener(AUTH_CHANGE_EVENT, syncSession);
    window.addEventListener("storage", syncSession);
    return () => {
      window.removeEventListener(AUTH_CHANGE_EVENT, syncSession);
      window.removeEventListener("storage", syncSession);
    };
  }, []);

  useEffect(() => {
    if (!accessToken || !role) return;
    let isCurrent = true;
    getMyProfileName(role)
      .then(({ firstName, lastName }) => {
        if (isCurrent) saveAuthProfileName(firstName, lastName);
      })
      .catch(() => {
        // The account dropdown falls back to the email if a profile is not available.
      });
    return () => {
      isCurrent = false;
    };
  }, [accessToken, role]);

  const handleSignOut = async () => {
    if (!session || isSigningOut) return;
    setIsSigningOut(true);
    try {
      await logout(session.refreshToken);
      notify("success", "You have signed out successfully.");
    } catch (error) {
      notify("error", getApiErrorMessage(error, "Could not end the server session. You have been signed out on this device."));
    } finally {
      clearAuthSession();
      setSession(null);
      setIsSigningOut(false);
      setIsMobileMenuOpen(false);
      scrollToTop();
      navigate("/", { replace: true });
    }
  };

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
          {session ? (
            <>
              <div className="flex items-center gap-5 text-white/85" aria-label="Account shortcuts">
                <span title="Notifications" aria-label="Notifications">
                  <i className="fa-regular fa-bell text-lg transition-colors hover:text-red-01" aria-hidden="true" />
                </span>
                <span title="Terms of Use" aria-label="Terms of Use">
                  <i className="fa-regular fa-circle-question text-lg transition-colors hover:text-red-01" aria-hidden="true" />
                </span>
              </div>
              <UserMenu session={session} isSigningOut={isSigningOut} onSignOut={handleSignOut} />
            </>
          ) : (
            <>
              <NavLink
                to="/auth/sign-up"
                onClick={scrollToTop}
                className="text-[15px] font-bold text-white/90 transition-colors duration-500 hover:text-red-01"
              >
                Sign Up
              </NavLink>

              <NavLink
                to="/auth/login"
                onClick={scrollToTop}
                className="inline-flex font-bold items-center justify-center rounded-md bg-red-01 px-6 py-2.5 text-[15px] text-white shadow-sm transition-all duration-500 hover:bg-[white] hover:text-inherit"
              >
                Sign In
              </NavLink>
            </>
          )}
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
                    if (item.label === "Home") scrollToTop();
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
            {session ? (
              <UserMenu session={session} mobile isSigningOut={isSigningOut} onSignOut={handleSignOut} />
            ) : (
              <>
                <NavLink
                  to="/auth/sign-up"
                  onClick={() => {
                    scrollToTop();
                    setIsMobileMenuOpen(false);
                  }}
                  className="rounded-md py-3 text-center text-white/90 transition-colors hover:bg-white/5 hover:text-red-01"
                >
                  Sign Up
                </NavLink>

                <NavLink
                  to="/auth/login"
                  onClick={() => {
                    scrollToTop();
                    setIsMobileMenuOpen(false);
                  }}
                  className="rounded-md bg-red-01 py-3 text-center font-bold text-white transition-colors hover:bg-white hover:text-gray-900"
                >
                  Sign In
                </NavLink>
              </>
            )}
          </div>
        </nav>
      </div>
    </header>
  );
}

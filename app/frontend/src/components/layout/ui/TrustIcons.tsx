export default function TrustIcons({ name, className = "" }: { name: string; className?: string }) {
  const paths: Record<string, string> = {
    mail: "M3 5h18v14H3z M3 6l9 7 9-7",
    lock: "M5 10h14v11H5z M8 10V7a4 4 0 0 1 8 0v3",
    lock_reset: "M4 11V7a4 4 0 0 1 8 0v3 M4 7H1 M4 7l-3 3 M5 11h14v10H5z",
    visibility: "M2 12s3.6-7 10-7 10 7 10 7-3.6 7-10 7S2 12 2 12z M12 9a3 3 0 1 0 0 6 3 3 0 0 0 0-6z",
    visibility_off: "M3 3l18 18 M10.6 10.6a2 2 0 0 0 2.8 2.8 M9.9 5.2A10.8 10.8 0 0 1 12 5c6.4 0 10 7 10 7a16 16 0 0 1-3.1 3.8 M6.2 6.2C3.5 8 2 12 2 12s3.6 7 10 7a10 10 0 0 0 3-.5",
    arrow_forward: "M5 12h14 M13 6l6 6-6 6",
    arrow_back: "M19 12H5 M11 6l-6 6 6 6",
    mark_email_read: "M3 5h18v14H3z M3 6l9 7 9-7 M8 16l2 2 5-5",
    verified_user: "M12 22s8-4 8-11V5l-8-3-8 3v6c0 7 8 11 8 11z M9 12l2 2 4-4",
    check_circle: "M22 11.1V12a10 10 0 1 1-5.9-9.1 M22 4 12 14.01l-3-3",
    support_agent: "M3 12a9 9 0 0 1 18 0 M3 12v5h4v-6H3 M21 12v5h-4v-6h4 M17 20h-5",
    security: "M12 22s8-4 8-11V5l-8-3-8 3v6c0 7 8 11 8 11z",
  };

  return (
    <svg
      className={`jobtex-signup__icon ${className}`}
      viewBox="0 0 24 24"
      fill="none"
      stroke="currentColor"
      strokeWidth="1.8"
      strokeLinecap="round"
      strokeLinejoin="round"
      aria-hidden="true"
    >
      <path d={paths[name] ?? paths.check_circle} />
    </svg>
  );
}
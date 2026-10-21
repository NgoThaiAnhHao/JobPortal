import { Link } from "react-router";
import { getAuthSession } from "../../lib/api/authStorage";

export default function ProfileSettings() {
  const session = getAuthSession();

  if (!session) {
    return (
      <main className="mx-auto min-h-[50vh] max-w-3xl px-6 py-16 text-center">
        <h1 className="text-3xl font-bold text-slate-900">Sign in to view your profile</h1>
        <Link className="mt-6 inline-flex rounded-md bg-red-01 px-5 py-3 font-semibold text-white" to="/auth/login">
          Sign In
        </Link>
      </main>
    );
  }

  return (
    <main className="mx-auto min-h-[50vh] max-w-3xl px-6 py-16">
      <h1 className="text-3xl font-bold text-slate-900">Profile Settings</h1>
      <p className="mt-2 text-slate-600">Your account information saved on this device.</p>
      <section className="mt-8 rounded-xl border border-slate-200 bg-white p-6 shadow-sm" aria-label="Saved account information">
        <dl className="grid gap-5 sm:grid-cols-2">
          <div>
            <dt className="text-sm font-medium text-slate-500">Email</dt>
            <dd className="mt-1 break-all font-semibold text-slate-900">{session.user.email || "Not available"}</dd>
          </div>
          <div>
            <dt className="text-sm font-medium text-slate-500">Account type</dt>
            <dd className="mt-1 font-semibold capitalize text-slate-900">{session.user.role.replaceAll("_", " ").toLowerCase()}</dd>
          </div>
        </dl>
      </section>
    </main>
  );
}

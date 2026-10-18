import { useState, type SubmitEvent } from "react";
import { Loading } from "./ui/Loading";
import FadeUp from "./ui/FadeUp";

type JobNotificationsProps = {
  onSubscribe?: (email: string) => void | Promise<void>;
};

export default function JobNotifications({ onSubscribe }: JobNotificationsProps) {
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleSubmit = async (event: SubmitEvent<HTMLFormElement>) => {
    event.preventDefault();
    setMessage("");

    if (!onSubscribe) {
      setMessage("Job notifications are not connected yet.");
      return;
    }

    setIsSubmitting(true);
    try {
      await onSubscribe(email.trim());
      setMessage("Thanks for subscribing to job notifications.");
      setEmail("");
    } catch {
      setMessage("We couldn't subscribe you right now. Please try again.");
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <section className="job-notifications" aria-labelledby="job-notifications-title">
      <FadeUp className="job-notifications__inner">
        <div className="job-notifications__copy">
          <h2 id="job-notifications-title">Get New Job Notifications</h2>
          <p>Subscribe &amp; get all related jobs notification</p>
        </div>
        <form className="job-notifications__form" onSubmit={handleSubmit}>
          <label className="job-notifications__label" htmlFor="job-notifications-email">
            Email address
          </label>
          <input
            id="job-notifications-email"
            type="email"
            autoComplete="email"
            placeholder="Enter your email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            required
          />
          <button type="submit" disabled={isSubmitting}>
            {isSubmitting ? <><Loading /> Subscribing...</> : "Subscribe"}
          </button>
          {message && <span className="job-notifications__message" role="status">{message}</span>}
        </form>
      </FadeUp>
    </section>
  );
}

import { useState, type SubmitEvent } from "react";
import { useNavigate } from "react-router";
import { Link } from "react-router";
import "../auth/Auth.css";
import Trust from "../../components/layout/ui/Trust";
import FadeUp from "../../components/layout/ui/FadeUp";
import AuthHero from "../../components/layout/AuthHero";
import JobNotifications from "../../components/layout/JobNotifications";
import { Loading } from "../../components/layout/ui/Loading";
import { getApiErrorMessage } from "../../lib/api/agent";
import { login } from "../../lib/api/auth";
import { saveAuthSession } from "../../lib/api/authStorage";
import { useToast } from "../../components/layout/ui/toastContext";

type SignInProps = {
  onForgotPasswordClick?: () => void;
  onCreateAccountClick?: () => void;
  onGoogleSignInClick?: () => void;
};

function ArrowForwardIcon() {
  return (
    <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
      <path d="M5 12h14M13 6l6 6-6 6" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" />
    </svg>
  );
}

function EyeIcon({ visible }: { visible: boolean }) {
  return visible ? (
    <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
      <path d="M3 3l18 18M10.6 10.6a2 2 0 0 0 2.8 2.8M9.9 5.2A10.8 10.8 0 0 1 12 5c6.4 0 10 7 10 7a16 16 0 0 1-3.1 3.8M6.2 6.2C3.5 8 2 12 2 12s3.6 7 10 7a10 10 0 0 0 3-.5" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" />
    </svg>
  ) : (
    <svg viewBox="0 0 24 24" fill="none" aria-hidden="true">
      <path d="M2 12s3.6-7 10-7 10 7 10 7-3.6 7-10 7S2 12 2 12Z" stroke="currentColor" strokeWidth="1.8" strokeLinecap="round" strokeLinejoin="round" />
      <circle cx="12" cy="12" r="3" stroke="currentColor" strokeWidth="1.8" />
    </svg>
  );
}

function GoogleIcon() {
  return (
    <svg className="jobtex-signin__google-icon" viewBox="0 0 24 24" aria-hidden="true">
      <path fill="#4285F4" d="M22.56 12.25c0-.78-.07-1.53-.2-2.25H12v4.26h5.92c-.26 1.37-1.04 2.53-2.21 3.31v2.77h3.57c2.08-1.92 3.28-4.74 3.28-8.09z" />
      <path fill="#34A853" d="M12 23c2.97 0 5.46-.98 7.28-2.66l-3.57-2.77c-.98.66-2.23 1.06-3.71 1.06-2.86 0-5.29-1.93-6.16-4.53H2.18v2.84C3.99 20.53 7.7 23 12 23z" />
      <path fill="#FBBC05" d="M5.84 14.09c-.22-.66-.35-1.36-.35-2.09s.13-1.43.35-2.09V7.06H2.18A11 11 0 0 0 1 12c0 1.78.43 3.45 1.18 4.94l2.85-2.22.81-.63z" />
      <path fill="#EA4335" d="M12 5.38c1.62 0 3.06.56 4.21 1.64l3.15-3.15C17.45 2.09 14.97 1 12 1 7.7 1 3.99 3.47 2.18 7.06l3.66 2.84c.87-2.6 3.3-4.52 6.16-4.52z" />
    </svg>
  );
}

export default function SignIn({
  onForgotPasswordClick,
  onCreateAccountClick,
  onGoogleSignInClick,
}: SignInProps) {
  const navigate = useNavigate();
  const { notify } = useToast();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [rememberMe, setRememberMe] = useState(false);
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [googleMessage, setGoogleMessage] = useState("");

  const handleSubmit = async (event: SubmitEvent) => {
    event.preventDefault();
    setError("");
    setGoogleMessage("");

    const normalizedEmail = email.trim();
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(normalizedEmail)) {
      setError("Please enter a valid email address.");
      return;
    }
    if (!password) {
      setError("Please enter your password.");
      return;
    }
    setIsSubmitting(true);
    try {
      const session = await login({ email: normalizedEmail, password });
      saveAuthSession(session, rememberMe);
      notify("success", "Signed in successfully.");
      window.scrollTo({ top: 0, left: 0, behavior: "instant" });
      navigate("/", { replace: true });
    } catch (requestError) {
      notify("error", getApiErrorMessage(requestError, "Unable to sign in. Please check your credentials and try again."));
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleGoogleClick = () => {
    if (onGoogleSignInClick) {
      onGoogleSignInClick();
      return;
    }
    setGoogleMessage("Google sign-in is not connected yet.");
  };

  return (
    <>
    <AuthHero title="Sign In" />
    <section className="auth-page jobtex-signin" aria-labelledby="jobtex-signin-title">
      <div className="jobtex-signin__ambient jobtex-signin__ambient--top" aria-hidden="true" />
      <div className="jobtex-signin__ambient jobtex-signin__ambient--bottom" aria-hidden="true" />

      <FadeUp className="auth-shell jobtex-signin__content">
        <div className="auth-card jobtex-signin__card">
          <header className="auth-header jobtex-signin__header">
            <h1 id="jobtex-signin-title">Sign In</h1>
            <p>Log in to your jobtex account to access job applications and talent profiles.</p>
          </header>

          <form className="jobtex-signin__form" onSubmit={handleSubmit} noValidate>
            <div className="jobtex-signin__field">
              <label htmlFor="jobtex-signin-email">Enter Email</label>
              <input
                id="jobtex-signin-email"
                name="email"
                type="email"
                autoComplete="username"
                placeholder="Enter Your Email"
                value={email}
                onChange={(event) => setEmail(event.target.value)}
                required
              />
            </div>

            <div className="jobtex-signin__field">
              <label htmlFor="jobtex-signin-password">Enter Password</label>
              <div className="jobtex-signin__password-wrap">
                <input
                  id="jobtex-signin-password"
                  name="password"
                  type={showPassword ? "text" : "password"}
                  autoComplete="current-password"
                  placeholder="Enter Your Password"
                  value={password}
                  onChange={(event) => setPassword(event.target.value)}
                  required
                />
                <button
                  className="jobtex-signin__visibility"
                  type="button"
                  aria-label={showPassword ? "Hide password" : "Show password"}
                  aria-pressed={showPassword}
                  onClick={() => setShowPassword((visible) => !visible)}
                >
                  <EyeIcon visible={showPassword} />
                </button>
              </div>
            </div>

            <div className="jobtex-signin__options">
              <label className="jobtex-signin__remember">
                <input
                  type="checkbox"
                  checked={rememberMe}
                  onChange={(event) => setRememberMe(event.target.checked)}
                />
                <span>Remember this device</span>
              </label>
              <a href="/forgot-password" onClick={onForgotPasswordClick}>Forgot Password?</a>
            </div>

            {error && <p className="jobtex-signin__error" role="alert">{error}</p>}

            <button className="jobtex-signin__submit" type="submit" disabled={isSubmitting}>
              {isSubmitting ? (
                <><Loading /><span>Signing In...</span></>
              ) : (
                <><span>Sign In</span><ArrowForwardIcon /></>
              )}
            </button>

            <div className="jobtex-signin__social">
              <div className="jobtex-signin__divider">
                <span>Or sign in with</span>
              </div>
              <button className="jobtex-signin__google" type="button" onClick={handleGoogleClick}>
                <GoogleIcon />
                <span>Continue with Google</span>
              </button>
              {googleMessage && <p className="jobtex-signin__status" role="status">{googleMessage}</p>}
            </div>

            <div className="jobtex-signin__create-account">
              <p>
                Not have an account?{" "}
              <Link to="/auth/sign-up" onClick={onCreateAccountClick}>
                  Create an account <span aria-hidden="true">»</span>
              </Link>
              </p>
            </div>
          </form>
        </div>

        <Trust />
      </FadeUp>
    </section>
    <JobNotifications />
    </>
  );
}

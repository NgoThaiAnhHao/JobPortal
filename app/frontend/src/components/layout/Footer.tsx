import "./Footer.css";
import FadeUp from "./ui/FadeUp";
import Logo from "./ui/Logo";

type FooterLink = {
  label: string;
  href: string;
};

const candidateLinks: FooterLink[] = [
  { label: "Browse Jobs", href: "/jobs" },
  { label: "Account", href: "/account" },
  { label: "Browse Categories", href: "/categories" },
  { label: "Resume", href: "/resume" },
  { label: "Job List", href: "/jobs" },
  { label: "Sign Up", href: "/register" },
];

const quickLinks: FooterLink[] = [
  { label: "Home", href: "/" },
  { label: "About", href: "/about" },
  { label: "FAQ", href: "/faq" },
  { label: "Pricing", href: "/pricing" },
  { label: "Privacy", href: "/privacy" },
  { label: "Contact", href: "/contact" },
];

function SocialIcon({ name }: { name: "facebook" | "twitter" | "pinterest" | "linkedin" }) {
  if (name === "facebook") {
    return <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M9 8H6v4h3v12h5V12h3.642L18 8h-4V6.333C14 5.374 14.5 5 15.6 5H18V0h-3.808C10.597 0 9 1.583 9 4.615V8z" /></svg>;
  }
  if (name === "twitter") {
    return <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M24 4.557c-.883.392-1.832.656-2.828.775 1.017-.609 1.798-1.574 2.165-2.724-.951.564-2.005.974-3.127 1.195-.897-.957-2.178-1.555-3.594-1.555-3.179 0-5.515 2.966-4.797 6.045-4.091-.205-7.719-2.165-10.148-5.144-1.29 2.213-.669 5.108 1.523 6.574-.806-.026-1.566-.247-2.229-.616-.054 2.281 1.581 4.415 3.949 4.89-.693.188-1.452.232-2.224.084.626 1.956 2.444 3.379 4.6 3.419-2.07 1.623-4.678 2.348-7.29 2.04 2.179 1.397 4.768 2.212 7.548 2.212 9.142 0 14.307-7.721 13.995-14.646.962-.695 1.797-1.562 2.457-2.549z" /></svg>;
  }
  if (name === "pinterest") {
    return <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M12 0C5.373 0 0 5.372 0 12c0 5.084 3.163 9.426 7.627 11.174-.105-.949-.2-2.405.042-3.441.218-.937 1.407-5.965 1.407-5.965s-.359-.719-.359-1.782c0-1.668.967-2.914 2.171-2.914 1.023 0 1.518.769 1.518 1.69 0 1.029-.655 2.568-.994 3.995-.283 1.194.599 2.169 1.777 2.169 2.133 0 3.772-2.249 3.772-5.495 0-2.873-2.064-4.882-5.012-4.882-3.414 0-5.418 2.561-5.418 5.207 0 1.031.397 2.138.893 2.738a.36.36 0 0 1 .083.345l-.333 1.36c-.053.22-.174.267-.402.161-1.499-.698-2.436-2.889-2.436-4.649 0-3.785 2.75-7.262 7.929-7.262 4.163 0 7.398 2.967 7.398 6.931 0 4.136-2.607 7.464-6.227 7.464-1.216 0-2.359-.631-2.75-1.378l-.748 2.853c-.271 1.043-1.002 2.35-1.492 3.146C9.57 23.812 10.763 24 12 24c6.627 0 12-5.373 12-12 0-6.628-5.373-12-12-12z" /></svg>;
  }
  return <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M19 0h-14c-2.761 0-5 2.239-5 5v14c0 2.761 2.239 5 5 5h14c2.762 0 5-2.239 5-5v-14c0-2.761-2.238-5-5-5zm-11 19h-3v-11h3v11zm-1.5-12.268c-.966 0-1.75-.79-1.75-1.764s.784-1.764 1.75-1.764 1.75.79 1.75 1.764-.783 1.764-1.75 1.764zm13.5 12.268h-3v-5.604c0-3.368-4-3.113-4 0v5.604h-3v-11h3v1.765c1.396-2.586 7-2.777 7 2.476v6.759z" /></svg>;
}

function ContactIcon({ type }: { type: "phone" | "email" | "location" }) {
  if (type === "phone") {
    return <svg viewBox="0 0 24 24" fill="none" aria-hidden="true"><path d="M3 5a2 2 0 0 1 2-2h3.28a1 1 0 0 1 .948.684l1.498 4.493a1 1 0 0 1-.502 1.21l-2.257 1.13a11.042 11.042 0 0 0 5.516 5.516l1.13-2.257a1 1 0 0 1 1.21-.502l4.493 1.498a1 1 0 0 1 .684.949V19a2 2 0 0 1-2 2h-1C9.716 21 3 14.284 3 6V5z" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" /></svg>;
  }
  if (type === "email") {
    return <svg viewBox="0 0 24 24" fill="none" aria-hidden="true"><path d="m3 8 7.89 5.26a2 2 0 0 0 2.22 0L21 8M5 19h14a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2H5a2 2 0 0 0-2 2v10a2 2 0 0 0 2 2z" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" /></svg>;
  }
  return <svg viewBox="0 0 24 24" fill="none" aria-hidden="true"><path d="M17.657 16.657 13.414 20.9a1.998 1.998 0 0 1-2.827 0l-4.244-4.243a8 8 0 1 1 11.314 0z" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" /><path d="M15 11a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" /></svg>;
}

function LinkColumn({ title, links }: { title: string; links: FooterLink[] }) {
  return (
    <nav className="jobtex-footer__column" aria-label={title}>
      <h2 className="jobtex-footer__heading">{title}</h2>
      <ul className="jobtex-footer__link-list">
        {links.map((link) => (
          <li key={`${title}-${link.label}`}>
            <a className="jobtex-footer__link" href={link.href}>
              <span className="jobtex-footer__chevron" aria-hidden="true">»</span>
              <span>{link.label}</span>
            </a>
          </li>
        ))}
      </ul>
    </nav>
  );
}

export default function Footer() {
  return (
    <footer className="jobtex-footer">
      <FadeUp className="jobtex-footer__main">
        <div className="jobtex-footer__grid">
          <section className="jobtex-footer__brand" aria-label="About Jobtex">
              <Logo />
              
            <p className="jobtex-footer__description">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor incididunt ut labore et dolore magna.
            </p>
            <div className="jobtex-footer__socials" aria-label="Social media">
              <a href="https://www.facebook.com/" target="_blank" rel="noreferrer" aria-label="Facebook"><SocialIcon name="facebook" /></a>
              <a href="https://twitter.com/" target="_blank" rel="noreferrer" aria-label="Twitter"><SocialIcon name="twitter" /></a>
              <a href="https://www.pinterest.com/" target="_blank" rel="noreferrer" aria-label="Pinterest"><SocialIcon name="pinterest" /></a>
              <a href="https://www.linkedin.com/" target="_blank" rel="noreferrer" aria-label="LinkedIn"><SocialIcon name="linkedin" /></a>
            </div>
          </section>

          <LinkColumn title="For Candidate" links={candidateLinks} />
          <LinkColumn title="Quick Links" links={quickLinks} />

          <section className="jobtex-footer__column">
            <h2 className="jobtex-footer__heading">Information</h2>
            <ul className="jobtex-footer__contact-list">
              <li>
                <ContactIcon type="phone" />
                <a href="tel:+101984754">+101 984 754</a>
              </li>
              <li>
                <ContactIcon type="email" />
                <a href="mailto:info@jobtex.com">info@jobtex.com</a>
              </li>
              <li>
                <ContactIcon type="location" />
                <span>123, Denver, USA</span>
              </li>
            </ul>
          </section>
        </div>
      </FadeUp>

      <FadeUp className="jobtex-footer__bottom">
        <div className="jobtex-footer__bottom-inner">
          <p>
            © Jobtex is Proudly Owned by{" "}
            <a href="https://hibootstrap.com/" target="_blank" rel="noreferrer">HiBootstrp</a>
          </p>
          <nav className="jobtex-footer__legal" aria-label="Legal links">
            <a href="/terms">Terms &amp; Conditions</a>
            <a href="/privacy">Privacy Policy</a>
          </nav>
        </div>
      </FadeUp>
    </footer>
  );
}

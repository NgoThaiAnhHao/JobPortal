import FadeUp from "./ui/FadeUp";

type AuthHeroProps = {
  title: string;
};

export default function AuthHero({ title }: AuthHeroProps) {
  return (
    <section className={`auth-hero ${title === "Sign In" ? "auth-hero--signin" : "auth-hero--signup"}`} aria-label={`${title} page heading`}>
      <FadeUp className="auth-hero__content">
        <h1>{title}</h1>
        <FadeUp className="auth-hero__breadcrumb" aria-label="Breadcrumb">
          <a href="/">Home</a>
          <span aria-hidden="true">›</span>
          <span aria-current="page">{title}</span>
        </FadeUp>
      </FadeUp>
    </section>
  );
}

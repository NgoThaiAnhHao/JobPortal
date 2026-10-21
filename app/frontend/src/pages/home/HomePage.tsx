import { useMemo, useState, type SubmitEvent } from "react";
import "./HomePage.css";
import FadeUp from "../../components/layout/ui/FadeUp";
import FadeLeft from "@/components/layout/ui/FadeLeft";

const categories = [
  { icon: "calculate", name: "Accountancy", count: 301 },
  { icon: "school", name: "Education", count: 210 },
  { icon: "build", name: "Automotive Jobs", count: 281 },
  { icon: "co_present", name: "Business", count: 122 },
  { icon: "favorite", name: "Health Care", count: 335 },
  { icon: "developer_mode", name: "IT & Agency", count: 401 },
  { icon: "engineering", name: "Engineering", count: 100 },
  { icon: "gavel", name: "Legal", count: 201 },
];

const jobs = [
  {
    title: "Web Designer, Graphic Designer, UI/UX Designer",
    role: "Graphics Designer",
    salary: "$35,000–$38,000",
    location: "London, UK",
    posted: "9 days ago",
    type: "Full Time",
    logo: "V",
    color: "#059669",
  },
  {
    title: "Website Developer & Software Developer",
    role: "Web Developer",
    salary: "$3,000–$8,000",
    location: "Garden Road, UK",
    posted: "5 days ago",
    type: "Full Time",
    logo: "✦",
    color: "#e11d48",
  },
  {
    title: "Senior Application Developer & Engineer",
    role: "IT & Software",
    salary: "$45,000–$55,000",
    location: "New York, USA",
    posted: "2 days ago",
    type: "Full Time",
    logo: "✓",
    color: "#2563eb",
  },
];

const companies = [
  {
    name: "Trophy & Sans",
    location: "Green Lanes, London",
    openings: 25,
    mark: "A",
    color: "#dd0031",
  },
  {
    name: "Trout Design",
    location: "Park Avenue, Mumbai",
    openings: 35,
    mark: "T",
    color: "#7c3aed",
  },
  {
    name: "Resland LTD",
    location: "Betas Quence, London",
    openings: 20,
    mark: "R",
    color: "#ef4444",
  },
  {
    name: "Lawn Hopper",
    location: "Wellesley Rd, London",
    openings: 45,
    mark: "L",
    color: "#059669",
  },
];

const plans = [
  {
    name: "Free Forever",
    price: "$0",
    icon: "sentiment_satisfied",
    features: [
      "Appear in search results",
      "Accept mobile candidate applications",
      "Manage candidates directly",
    ],
  },
  {
    name: "Sponsor",
    price: "$10",
    icon: "workspace_premium",
    popular: true,
    features: [
      "Premium listing placement",
      "PPC sponsored promotion on job",
      "Reach 3x more qualified candidates",
      "Desktop & mobile instant alerts",
    ],
  },
  {
    name: "Premium Plan",
    price: "$30",
    icon: "military_tech",
    features: [
      "Job ad live for six weeks",
      "Featured carousel spotlight",
      "Dedicated account recruiter support",
      "Full automated candidate screening",
    ],
  },
];

const candidates = [
  {
    name: "Alex Turner",
    role: "Full Stack Engineer",
    location: "Carlisle",
    image:
      "https://lh3.googleusercontent.com/aida-public/AB6AXuBjnK6m4vaWPA-lyhCRVNJUKWyJvIhtq491By1dBeRdK1O8oe7Can--7-vQUwWcmDXrgiEBcXhgXAAp_AgHwBjF8eYVg04UofB3FjY3i_BZdR270ZMfpMeSFt6s3u7ZphFigl2nUYDBrR7_zwNLHgzEBCaBEhZH-L16bSnbVF7AIlSLYDc_1ZIa1m7rSaz99HBT8mIpY7fi-Gv7q99L7PTukxyDbECw5Rv-317V7_2XdOtwM1xRxa7G",
  },
  {
    name: "Sophia Bennett",
    role: "Product Designer",
    location: "London",
    image:
      "https://images.unsplash.com/photo-1551836022-d5d88e9218df?auto=format&fit=crop&w=700&q=80",
  },
  {
    name: "Daniel Kim",
    role: "Software Engineer",
    location: "Singapore",
    image:
      "https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=700&q=80",
  },
  {
    name: "Olivia Martin",
    role: "Marketing Specialist",
    location: "Toronto",
    image:
      "https://images.unsplash.com/photo-1544005313-94ddf0286df2?auto=format&fit=crop&w=700&q=80",
  },
];

const articles = [
  {
    category: "Interview",
    date: "7 Feb, 2026",
    title: "How to Stand Out in Your Next Job Interview",
    description:
      "Practical ways to prepare, communicate your strengths, and leave a confident impression.",
    image:
      "https://lh3.googleusercontent.com/aida-public/AB6AXuCzrEdHSs3FrHfKkDu803u3mBYeCG2WdCdtGaOcx0UPEm_jqkftrLqxRi8I7lQj3ZUoj7RFSiCICrbGNBrSMNggTuWJJrg8gvjNs6mj2jBnVnsteax6P4GV79I_cPZv2t7S0DyG_6n02o56u29Xuaxrt5GHeAIaZGWb9wMniSA3-cJaGtuwOEblbmyM568uD6OAtNmXfEkSpPWQkM8-50tTUeCGHHix3pocXVc8HTOhKLqY75zw1eep",
  },
  {
    category: "Career Advice",
    date: "12 Feb, 2026",
    title: "Build a Resume Recruiters Want to Read",
    description:
      "Learn how to make your experience, skills, and achievements easy to scan.",
    image: "/images/resume-career-advice.jpg",
  },
  {
    category: "Technology",
    date: "18 Feb, 2026",
    title: "The Reason Why Software Developer Is a Great Career",
    description:
      "Explore the flexibility, growth, and global opportunities in modern tech roles.",
    image:
      "https://images.unsplash.com/photo-1498050108023-c5249f4df085?auto=format&fit=crop&w=900&q=80",
  },
];

function Icon({ name, className = "" }: { name: string; className?: string }) {
  return (
    <span
      aria-hidden="true"
      className={`material-symbols-outlined ${className}`}
    >
      {name}
    </span>
  );
}

function SectionHeading({
  title,
  description,
}: {
  title: string;
  description: string;
}) {
  return (
    <FadeUp className="home-section-heading">
      <h2>{title}</h2>
      <p>{description}</p>
    </FadeUp>
  );
}

export default function HomePage() {
  const [keyword, setKeyword] = useState("");
  const [location, setLocation] = useState("");
  const [savedJobs, setSavedJobs] = useState<string[]>([]);
  const [savedCandidates, setSavedCandidates] = useState<string[]>([]);
  const [searchMessage, setSearchMessage] = useState("");

  const filteredJobs = useMemo(() => {
    const query = keyword.trim().toLowerCase();
    const place = location.trim().toLowerCase();
    return jobs.filter(
      (job) =>
        (!query || `${job.title} ${job.role}`.toLowerCase().includes(query)) &&
        (!place || job.location.toLowerCase().includes(place)),
    );
  }, [keyword, location]);

  function handleSearch(event: SubmitEvent) {
    event.preventDefault();
    setSearchMessage(
      `${filteredJobs.length} sample ${filteredJobs.length === 1 ? "job" : "jobs"} match your search.`,
    );
    document
      .getElementById("home-jobs")
      ?.scrollIntoView({ behavior: "smooth", block: "start" });
  }

  function toggleSaved(
    value: string,
    list: string[],
    setList: (items: string[]) => void,
  ) {
    setList(
      list.includes(value)
        ? list.filter((item) => item !== value)
        : [...list, value],
    );
  }

  return (
    <main className="jobtex-home">
      <section className="home-hero">
        <div className="home-hero-photo" />
        <div className="home-hero-shade" />
        <FadeUp className="home-container home-hero-content">
          <FadeUp>
            <span className="home-eyebrow">
              Find Jobs, Employment &amp; Career Opportunities
            </span>
            <h1>Drop Resume &amp; Get Your Desire Job!</h1>
          </FadeUp>

          {/* Search */}
          <form className="home-search-panel" onSubmit={handleSearch}>
            <label className="home-search-field">
              <span>Keyword:</span>
              <div>
                <Icon name="search" />
                <input
                  value={keyword}
                  onChange={(e) => setKeyword(e.target.value)}
                  placeholder="Job Title"
                  aria-label="Job title or keyword"
                />
              </div>
            </label>
            <label className="home-search-field">
              <span>Location:</span>
              <div>
                <Icon name="location_on" />
                <input
                  value={location}
                  onChange={(e) => setLocation(e.target.value)}
                  placeholder="City or State"
                  aria-label="City or state"
                />
              </div>
            </label>
            <button
              className="home-button home-button-red home-search-submit"
              type="submit"
            >
              Find a job <Icon name="search" />
            </button>
          </form>

          <FadeUp>
            <p className="home-trending">
              <strong>Trending Keywords:</strong> Automotive, Education, Health
              and Care Engineering
            </p>
          </FadeUp>
        </FadeUp>
        <div className="home-hero-curve" />
      </section>

      <section className="home-section home-categories">
        <div className="home-container">
          <SectionHeading
            title="Choose Your Category"
            description="Explore curated positions across top tier industrial sectors, matched instantly with your verified skills and credentials."
          />
          <FadeUp className="home-category-grid">
            {categories.map((category) => (
              <a
                className="home-category-card"
                href="#home-jobs"
                key={category.name}
              >
                <span className="home-category-icon">
                  <Icon name={category.icon} />
                </span>
                <h3>{category.name}</h3>
                <p>{category.count} open positions</p>
              </a>
            ))}
          </FadeUp>
        </div>
      </section>

      {/* Jobs You May Be Interested In Section */}
      <section className="home-section home-jobs-section" id="home-jobs">
        <div className="home-container">
          <SectionHeading
            title="Jobs You May Be Interested In"
            description="Explore selected opportunities from teams looking for their next great teammate."
          />
          {searchMessage && (
            <p className="home-search-feedback" role="status">
              {searchMessage}
            </p>
          )}
          {/* Jobs */}
          <FadeLeft className="home-job-list">
            {filteredJobs.map((job) => (
              <article className="home-job-card" key={job.title}>
                <div className="home-job-main">
                  <div
                    className="home-company-mark home-job-mark"
                    style={{ "--mark-color": job.color } as React.CSSProperties}
                  >
                    {job.logo}
                  </div>
                  <div className="home-job-copy">
                    <h3>{job.title}</h3>
                    <div className="home-job-meta">
                      <span>
                        <Icon name="work_outline" />
                        {job.role}
                      </span>
                      <span>
                        <Icon name="payments" />
                        {job.salary}
                      </span>
                      <span>
                        <Icon name="location_on" />
                        {job.location}
                      </span>
                      <span>
                        <Icon name="schedule" />
                        {job.posted}
                      </span>
                    </div>
                    <span className="home-pill">{job.type}</span>
                  </div>
                </div>
                <div className="home-job-actions">
                  <button
                    className={`home-icon-button ${savedJobs.includes(job.title) ? "is-saved" : ""}`}
                    onClick={() =>
                      toggleSaved(job.title, savedJobs, setSavedJobs)
                    }
                    aria-label={
                      savedJobs.includes(job.title) ? "Unsave job" : "Save job"
                    }
                  >
                    <Icon name="favorite" />
                  </button>
                  <a className="home-button home-button-red" href="#home-cta">
                    Browse Job
                  </a>
                </div>
              </article>
            ))}
            {filteredJobs.length === 0 && (
              <div className="home-empty-state">
                No sample jobs match those filters. Try another keyword or
                location.
              </div>
            )}
          </FadeLeft>
        </div>
      </section>

      {/* Easiest Way To Use Section */}
      <section className="home-process">
        <div className="home-process-overlay" />
        <div className="home-container home-process-content">
          <SectionHeading
            title="Easiest Way To Use"
            description="Three simple steps to move your career forward."
          />
          <FadeUp className="home-process-grid">
            {[
              {
                icon: "manage_search",
                title: "Browse Jobs",
                body: "Discover opportunities from companies that match your goals.",
              },
              {
                icon: "find_in_page",
                title: "Find Your Vacancy",
                body: "Compare roles, requirements, and benefits before applying.",
              },
              {
                icon: "description",
                title: "Submit Resume",
                body: "Share your experience and take the next step in your career.",
              },
            ].map((step, index) => (
              <div className="home-process-step" key={step.title}>
                <div className="home-process-icon">
                  <Icon name={step.icon} />
                  <span>{index + 1}</span>
                </div>
                <h3>{step.title}</h3>
                <p>{step.body}</p>
              </div>
            ))}
          </FadeUp>
        </div>
      </section>

      {/* Jobtex */}
      <section className="home-section home-companies">
        <div className="home-container">
          <SectionHeading
            title="Top Companies"
            description="Meet some of the teams building their future with jobtex."
          />
          <FadeUp className="home-company-grid">
            {companies.map((company) => (
              <article className="home-company-card" key={company.name}>
                <div
                  className="home-company-logo"
                  style={
                    { "--mark-color": company.color } as React.CSSProperties
                  }
                >
                  {company.mark}
                </div>
                <h3>{company.name}</h3>
                <p>
                  <Icon name="location_on" />
                  {company.location}
                </p>
                <a href="#home-jobs">{company.openings} Open Positions</a>
              </article>
            ))}
          </FadeUp>
        </div>
      </section>

      <section className="home-why">
        <FadeUp className="home-why-copy">
          <h2>Why You Choose Jobtex?</h2>
          <p className="home-why-intro">
            We leverage intelligent recruitment architecture to connect global
            industry innovators with agile, high-performing experts.
          </p>
          <FadeUp className="home-feature-grid">
            {[
              {
                icon: "groups",
                title: "Best Talented People",
                body: "Top professionals ready to make an impact from day one.",
              },
              {
                icon: "person_search",
                title: "Easy To Find Candidate",
                body: "Discover relevant candidates with skills that fit your needs.",
              },
              {
                icon: "forum",
                title: "Easy To Communicate",
                body: "Keep conversations and hiring steps moving smoothly.",
              },
              {
                icon: "public",
                title: "Global Recruitment Option",
                body: "Connect with talent and opportunities across borders.",
              },
            ].map((feature) => (
              <div className="home-feature" key={feature.title}>
                <span>
                  <Icon name={feature.icon} />
                </span>
                <div>
                  <h3>{feature.title}</h3>
                  <p>{feature.body}</p>
                </div>
              </div>
            ))}
          </FadeUp>
          <div className="home-metrics">
            {[
              { value: "127+", label: "Jobs Posted" },
              { value: "137+", label: "Jobs Filled" },
              { value: "180+", label: "Companies" },
              { value: "144k", label: "Members" },
            ].map((metric) => (
              <div key={metric.label}>
                <strong>{metric.value}</strong>
                <span>{metric.label}</span>
              </div>
            ))}
          </div>
        </FadeUp>
        <FadeLeft
          className="home-why-image"
          aria-label="Professional woman working at a bright modern workspace"
        />
      </section>

      <section className="home-section home-cta-section" id="home-cta">
        <div className="home-container home-cta-grid">
          <FadeUp className="home-cta-card home-cta-red">
            <span>Talent Portal</span>
            <h2>Looking For a Job?</h2>
            <p>
              Your next role could be with one of these leading organizations.
              Fast-track your application today.
            </p>
            <a href="#home-jobs">
              Apply Now <Icon name="arrow_forward" />
            </a>
          </FadeUp>
          <FadeLeft className="home-cta-card home-cta-navy">
            <span>Hiring Managers</span>
            <h2>Are You Recruiting?</h2>
            <p>
              Publish openings to a talented candidate pool and find your next
              great hire.
            </p>
            <a href="#home-plans">
              Post a Vacancy <Icon name="arrow_forward" />
            </a>
          </FadeLeft>
        </div>
      </section>

      <section className="home-section home-plans" id="home-plans">
        <div className="home-container">
          <SectionHeading
            title="Buy Our Plans & Packages"
            description="Transparent, high-impact recruitment packages designed for independent startups through enterprise recruitment teams."
          />
          <FadeUp className="home-plan-grid">
            {plans.map((plan) => (
              <article
                className={`home-plan-card ${plan.popular ? "home-plan-popular" : ""}`}
                key={plan.name}
              >
                {plan.popular && (
                  <span className="home-popular-badge">Popular</span>
                )}
                <span className="home-plan-icon">
                  <Icon name={plan.icon} />
                </span>
                <h3>{plan.name}</h3>
                <div className="home-plan-price">
                  {plan.price}
                  <small>/Month</small>
                </div>
                <ul>
                  {plan.features.map((feature) => (
                    <li key={feature}>
                      <Icon name="check_circle" />
                      {feature}
                    </li>
                  ))}
                </ul>
                <a className="home-button home-button-red" href="#home-jobs">
                  Find A Job
                </a>
                <span className="home-plan-accent" />
              </article>
            ))}
          </FadeUp>
        </div>
      </section>

      <section className="home-section home-candidates">
        <div className="home-container">
          <FadeUp className="home-section-title-row">
            <div>
              <h2>Featured Candidates</h2>
              <p>
                Top reviewed industry specialists open for new career
                milestones.
              </p>
            </div>
            <a href="#home-cta">
              View All Candidates <Icon name="chevron_right" />
            </a>
          </FadeUp>
          <FadeUp className="home-candidate-grid">
            {candidates.map((candidate) => (
              <article className="home-candidate-card" key={candidate.name}>
                <div className="home-candidate-photo">
                  <img
                    src={candidate.image}
                    alt={`${candidate.name}, ${candidate.role}`}
                    loading="lazy"
                  />
                  <button
                    className={`home-favorite ${savedCandidates.includes(candidate.name) ? "is-saved" : ""}`}
                    onClick={() =>
                      toggleSaved(
                        candidate.name,
                        savedCandidates,
                        setSavedCandidates,
                      )
                    }
                    aria-label={
                      savedCandidates.includes(candidate.name)
                        ? "Remove candidate from favorites"
                        : "Favorite candidate"
                    }
                  >
                    <Icon name="favorite" />
                  </button>
                </div>
                <div className="home-candidate-info">
                  <h3>{candidate.name}</h3>
                  <span>{candidate.role}</span>
                  <div>
                    <small>
                      <Icon name="location_on" />
                      {candidate.location}
                    </small>
                    <small>4D ago</small>
                  </div>
                </div>
              </article>
            ))}
          </FadeUp>
        </div>
      </section>

      <section className="home-section home-articles">
        <div className="home-container">
          <SectionHeading
            title="News, Tips & Articles"
            description="Insights, market reports, and strategic guidance for ambitious job hunters and talent leaders."
          />
          <FadeUp className="home-article-grid">
            {articles.map((article) => (
              <article className="home-article-card" key={article.title}>
                <div className="home-article-image">
                  <img src={article.image} alt="" loading="lazy" />
                  <span>{article.category}</span>
                </div>
                <div className="home-article-content">
                  <p className="home-article-date">
                    <Icon name="calendar_today" />
                    {article.date}
                  </p>
                  <h3>{article.title}</h3>
                  <p>{article.description}</p>
                  <a href="#home-cta">
                    Read More <span aria-hidden="true">+</span>
                  </a>
                </div>
              </article>
            ))}
          </FadeUp>
        </div>
      </section>
    </main>
  );
}

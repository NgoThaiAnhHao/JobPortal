# Jovie Home Page component

Files:
- `Home.tsx` — self-contained React + TypeScript Home page content. It does not render a Navbar or Footer.
- `Home.css` — scoped styling for the Home page, responsive breakpoints, hover states, and typography.

## Integration

Copy both files into your project's preferred pages folder, keeping `Home.tsx` and `Home.css` together. Then import:

```tsx
import Home from "./pages/Home";

// Render Home beneath your existing Navbar:
<>
  <Navbar />
  <Home />
</>
```

No routing or package configuration is included or changed. The component uses React only; icons and IBM Plex Sans are loaded from Google Fonts by the CSS. Hero/candidate/article imagery references remote URLs from the provided HTML/design and therefore requires an internet connection. Replace those URLs with local assets when integrating if preferred.

The search filters the sample job cards by title/role and location; saved-job and favorite-candidate buttons are local UI state only. Connect them to your API when ready.

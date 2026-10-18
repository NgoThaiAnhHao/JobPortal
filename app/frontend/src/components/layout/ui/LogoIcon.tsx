export default function LogoIcon({ compact = false }: { compact?: boolean }) {
  return (
    <div className={`flex items-center justify-center rounded-full border-2 border-white/90 text-white transition-all duration-300 ${compact ? "h-7 w-7 text-sm" : "h-8 w-8 text-base"}`}>
      <i className="fa-solid fa-headphones-simple" />
    </div>
  );
}

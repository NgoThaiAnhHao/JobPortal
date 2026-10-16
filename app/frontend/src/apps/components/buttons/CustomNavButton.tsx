import "./CustomButton.css";

type Props = {
    children: string
}

export default function CustomNavButton({children}: Props) {
  return (
    <button>
        <span className="box">
            {children}
        </span>
    </button>
  )
}
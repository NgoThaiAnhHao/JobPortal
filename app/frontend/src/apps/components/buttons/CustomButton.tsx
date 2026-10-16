import "./CustomButton.css";

type Props = {
    children: string
}

export default function CustomButton({children}: Props) {
  return (
    <>
        <button className="customBtn">
            {children}
        </button>
    </>
  )
}
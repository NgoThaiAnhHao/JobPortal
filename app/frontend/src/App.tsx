import { CssBaseline, Container } from "@mui/material"
import { Outlet } from "react-router"
import Navbar from "./apps/components/Navbar"


function App() {

  return (
    <>
      <CssBaseline />
      <Navbar />
      <Container sx={{ maxWidth: "xl", mt: 3 }}>
            <Outlet />
      </Container>
    </>
  )
}

export default App

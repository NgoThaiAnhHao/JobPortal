import { Box, CssBaseline } from "@mui/material"
import Navbar from "./components/layout/Navbar"
import { Outlet } from "react-router"

function App() {

  return (
    <>
      <Box>
        <CssBaseline />
        <Navbar />
        <Outlet />
      </Box>
    </>
  )
}

export default App

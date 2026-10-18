import { Box, CssBaseline } from "@mui/material"
import Navbar from "./components/layout/Navbar"
import { Outlet } from "react-router"
import Footer from "./components/layout/Footer"
import { ToastProvider } from "./components/layout/ui/ToastProvider"

function App() {

  return (
    <ToastProvider>
      <Box>
        <CssBaseline />
        <Navbar />
        <Outlet />
        <Footer />
      </Box>
    </ToastProvider>
  )
}

export default App

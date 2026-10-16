import { AppBar, Box, Container, Toolbar, Typography } from "@mui/material";
import HomeRepairServiceIcon from '@mui/icons-material/HomeRepairService';
import { grey } from '@mui/material/colors';
import CustomButton from "./buttons/CustomButton";
import CustomNavButton from "./buttons/CustomNavButton";


const logoColor = 'rgb(20, 160, 119)';
const bgColor = grey['A100']

export default function Navbar() {
  return (
    <AppBar position="static">
        <Container maxWidth="xl" sx={{bgcolor: bgColor}}>
            <Toolbar sx={{display: 'flex', justifyContent: 'space-between', alignItems: 'center'}}>
                {/* Left Content */}
                <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'end', gap: 1}}>
                    <HomeRepairServiceIcon sx={{fontSize: "2.9rem", color: logoColor}}/>
                    <Typography variant="h4" sx={{fontWeight: 'bold', color: 'black', fontStyle: 'italic', letterSpacing: '0.05rem'}}>Jobtex</Typography>
                </Box>

                {/* Middle Content */}
                <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', gap: 2}}>
                    <CustomButton>Jobs</CustomButton>
                    <CustomButton>Companies</CustomButton>
                    <CustomButton>About</CustomButton>
                    <CustomButton>Contact</CustomButton>
                </Box>

                {/* Right Content */}
                <Box sx={{display: 'flex', justifyContent: 'center', alignItems: 'center', gap: 1}}>
                    <CustomNavButton>
                        Sign In
                    </CustomNavButton>
                    <CustomNavButton >
                        Get started
                    </CustomNavButton>
                </Box>
            </Toolbar>
        </Container>
    </AppBar>
  )
}
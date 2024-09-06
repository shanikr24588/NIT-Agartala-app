package com.shani.nita.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shani.nita.adminScreens.AdminDashboard
import com.shani.nita.adminScreens.BannerUpdate
import com.shani.nita.adminScreens.FacultyDetailScreen
import com.shani.nita.adminScreens.FacultyUpdate
import com.shani.nita.adminScreens.MessDaysScreen
import com.shani.nita.adminScreens.MessItemScreen
import com.shani.nita.adminScreens.NoticeUpdate
import com.shani.nita.adminScreens.Noticelist
import com.shani.nita.adminScreens.UpdateEvents
import com.shani.nita.adminScreens.UpdateMessMenu
import com.shani.nita.adminScreens.UpdateScholarship
import com.shani.nita.AdminPanel_itemview.PdfRenderer
import com.shani.nita.adminScreens.AdminLoginScreen
import com.shani.nita.adminScreens.CalenderUpdate
import com.shani.nita.adminScreens.NotesBranchScreen
import com.shani.nita.adminScreens.NotesScreen
import com.shani.nita.adminScreens.NotesSemesterScreen
import com.shani.nita.adminScreens.Notes_Pyq_PinDocsScreen
import com.shani.nita.adminScreens.PinDocsBranchScreen
import com.shani.nita.adminScreens.PinDocsScreen
import com.shani.nita.adminScreens.PinDocsSemesterScreen
import com.shani.nita.adminScreens.PyqBranchScreen
import com.shani.nita.adminScreens.PyqScreen
import com.shani.nita.adminScreens.PyqSemesterScreen
import com.shani.nita.userScreens.MessMenu.ItemsScreen
import com.shani.nita.userScreens.BioTech.BioTech

import com.shani.nita.userScreens.Facilities.Chat
import com.shani.nita.userScreens.Chemical.Chemical
import com.shani.nita.userScreens.Chemistry.Chemistry
import com.shani.nita.userScreens.Civil.Civil
import com.shani.nita.userScreens.Facilities.Clubs
import com.shani.nita.userScreens.CSE.ComputerScience
import com.shani.nita.userScreens.EE.Electrical
import com.shani.nita.userScreens.ECE.Electronics
import com.shani.nita.userScreens.Facilities.Events.Events
import com.shani.nita.userScreens.ForgotPassword
import com.shani.nita.userScreens.HomeScreen
import com.shani.nita.userScreens.EIE.Instrumentation
import com.shani.nita.userScreens.LoginScreen
import com.shani.nita.userScreens.Mathematics.Mathematics
import com.shani.nita.userScreens.ME.Mechanical
import com.shani.nita.userScreens.MessMenu.MessMenu
import com.shani.nita.adminScreens.UpdateBranch
import com.shani.nita.userScreens.Administrators.AdministratorsScreen
import com.shani.nita.userScreens.BioTech.Faculty.BioFacultyScreen
import com.shani.nita.userScreens.BioTech.Notes.BioNotesPagerScreen
import com.shani.nita.userScreens.BioTech.PYQ.BioPYQPagerScreen
import com.shani.nita.userScreens.BioTech.PinDocs.BioPinDocsPagerScreen
import com.shani.nita.userScreens.CSE.Faculty.CseFacultyScreen
import com.shani.nita.userScreens.CSE.Notes.CseNotesPagerScreen
import com.shani.nita.userScreens.CSE.PYQ.CsePYQPagerScreen
import com.shani.nita.userScreens.CSE.PinDocs.CsePinDocsPagerScreen
import com.shani.nita.userScreens.Chemical.Faculty.ChemicalFacultyScreen
import com.shani.nita.userScreens.Chemical.Notes.ChemicalNotesPagerScreen
import com.shani.nita.userScreens.Chemical.PYQ.ChemicalPYQPagerScreen
import com.shani.nita.userScreens.Chemical.PinDocs.ChemicalPinDocsPagerScreen
import com.shani.nita.userScreens.Chemistry.Faculty.ChemistryFacultyScreen
import com.shani.nita.userScreens.Chemistry.Notes.ChemistryNotesPagerScreen
import com.shani.nita.userScreens.Chemistry.PYQ.ChemistryPYQPagerScreen
import com.shani.nita.userScreens.Chemistry.PinDocs.ChemistryPinDocsPagerScreen
import com.shani.nita.userScreens.Civil.Faculty.CivilFacultyScreen
import com.shani.nita.userScreens.Civil.Notes.CivilNotesPagerScreen
import com.shani.nita.userScreens.Civil.PYQ.CivilPYQPagerScreen
import com.shani.nita.userScreens.Civil.PinDocs.CivilPinDocsPagerScreen
import com.shani.nita.userScreens.ContactUs
import com.shani.nita.userScreens.ECE.Faculty.EceFacultyScreen
import com.shani.nita.userScreens.ECE.Notes.EceNotesPagerScreen
import com.shani.nita.userScreens.ECE.PYQ.EcePYQPagerScreen
import com.shani.nita.userScreens.ECE.PinDocs.EcePinDocsPagerScreen
import com.shani.nita.userScreens.EE.Faculty.EeFacultyScreen
import com.shani.nita.userScreens.EE.Notes.EeNotesPagerScreen
import com.shani.nita.userScreens.EE.PYQ.EePYQPagerScreen
import com.shani.nita.userScreens.EE.PinDocs.EePinDocsPagerScreen
import com.shani.nita.userScreens.EIE.Faculty.EieFacultyScreen
import com.shani.nita.userScreens.EIE.Notes.EieNotesPagerScreen
import com.shani.nita.userScreens.EIE.PYQ.EiePYQPagerScreen
import com.shani.nita.userScreens.EIE.PinDocs.EiePinDocsPagerScreen
import com.shani.nita.userScreens.Facilities.Notice.Notice
import com.shani.nita.userScreens.Physics.Physics
import com.shani.nita.userScreens.Production.Production
import com.shani.nita.userScreens.ProfileScreen
import com.shani.nita.userScreens.RegistrationScreen
import com.shani.nita.userScreens.Facilities.Scholarship.Scholarship
import com.shani.nita.userScreens.FirstYear.FirstYear
import com.shani.nita.userScreens.FirstYear.Notes.firstYearNotesPagerScreen
import com.shani.nita.userScreens.FirstYear.PYQ.firstYearPYQPagerScreen
import com.shani.nita.userScreens.FirstYear.PinDocs.firstYearPinDocsPagerScreen
import com.shani.nita.userScreens.ME.Faculty.MechFacultyScreen
import com.shani.nita.userScreens.ME.Notes.MechNotesPagerScreen
import com.shani.nita.userScreens.ME.PYQ.MechPYQPagerScreen
import com.shani.nita.userScreens.ME.PinDocs.MechPinDocsPagerScreen
import com.shani.nita.userScreens.Mathematics.Faculty.MathFacultyScreen
import com.shani.nita.userScreens.Mathematics.Notes.MathNotesPagerScreen
import com.shani.nita.userScreens.Mathematics.PYQ.MathPYQPagerScreen
import com.shani.nita.userScreens.Mathematics.PinDocs.MathPinDocsPagerScreen
import com.shani.nita.userScreens.MessMenu.PagerScreen
import com.shani.nita.userScreens.Physics.Faculty.PhyFacultyScreen
import com.shani.nita.userScreens.Physics.Notes.PhyNotesPagerScreen
import com.shani.nita.userScreens.Physics.PYQ.PhyPYQPagerScreen
import com.shani.nita.userScreens.Physics.PinDocs.PhyPinDocsPagerScreen
import com.shani.nita.userScreens.Production.Faculty.PeFacultyScreen
import com.shani.nita.userScreens.Production.Notes.PeNotesPagerScreen
import com.shani.nita.userScreens.Production.PYQ.PePYQPagerScreen
import com.shani.nita.userScreens.Production.PinDocs.PePinDocsPagerScreen
import com.shani.nita.userScreens.Splash

@Composable

fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash" ) {

        composable(route = "splash"){
            Splash(navController)
        }
        composable(route = "login") {
            LoginScreen(navController)
        }
        composable(route = "registration") {
            RegistrationScreen(navController)
        }
        composable(route = "forgot") {
            ForgotPassword(navController)
        }
        composable(route = "home") {
            HomeScreen(navController)
        }
        composable(route = "profile") {
            ProfileScreen(navController)

        }

        composable(route = "admin_login") {
            AdminLoginScreen(navController)

        }

        composable(route = "admin_dashboard") {
            AdminDashboard(navController)
        }
        composable(route = "notice") {
            Notice(navController)
        }
        composable(route = "mess_menu") {
            MessMenu(navController)
        }

        composable(route = "chat") {
            Chat(navController)
        }
        composable(route = "scholarship") {
            Scholarship(navController)
        }
        composable(route = "clubs") {
            Clubs(navController)
        }
        composable(route = "events") {
            Events(navController)
        }
        composable(route = "cse") {
            ComputerScience(navController)
        }
        composable(route = "ece") {
            Electronics(navController)
        }
        composable(route = "ee") {
            Electrical(navController)
        }
        composable(route = "eie") {
            Instrumentation(navController)
        }
        composable(route = "me") {
            Mechanical(navController)
        }
        composable(route = "ce") {
            Civil(navController)
        }
        composable(route = "che") {
            Chemical(navController)
        }
        composable(route = "pe") {
            Production(navController)
        }
        composable(route = "be") {
            BioTech(navController)
        }
        composable(route = "phy") {
            Physics(navController)
        }
        composable(route = "chem") {
            Chemistry(navController)
        }
        composable(route = "math") {
            Mathematics(navController)
        }
        composable(route = "banner_update") {
            BannerUpdate(navController)
        }
        composable(route = "notice_update") {
            NoticeUpdate(navController)
        }
        composable(route = "notice_list") {
            Noticelist(navController)
        }
        composable(
            route = "pdfScreen/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            PdfRenderer(url = url!!, navController)
        }

        composable(route = "faculty_update") {
            FacultyUpdate(navController)
        }
        composable(
            route = "facultydetail/{catName}",
            arguments = listOf(navArgument("catName") { type = NavType.StringType })
        ) { backStackEntry ->
            val catName = backStackEntry.arguments!!.getString("catName")
            if (catName != null) {
                FacultyDetailScreen(navController, catName)
            }
        }

        composable(route = "scholarship_update") {
            UpdateScholarship(navController)
        }

        composable(route = "events_update") {
            UpdateEvents(navController)
        }

        composable(route = "messmenu_update") {
            UpdateMessMenu(navController)
        }

        composable(route = "department_update") {
            UpdateBranch(navController)
        }



        composable(
            route = "day/{hostel}",
            arguments = listOf(navArgument("hostel") { type = NavType.StringType })
        ) { backStackEntry ->
            val hostel = backStackEntry.arguments!!.getString("hostel")
            if (hostel != null) {
                MessDaysScreen(navController, hostel)
            }
        }

        composable(
            route = "MessItems/{hostel}/{day}",
            arguments = listOf(
                navArgument("hostel") { type = NavType.StringType },
                navArgument("day") { type = NavType.StringType })
        ) { backStackEntry ->
            val hostel = backStackEntry.arguments!!.getString("hostel")
            val day = backStackEntry.arguments!!.getString("day")
            if (hostel != null && day != null) {
                MessItemScreen(navController, hostel, day)
            }


        }

        composable(
            route = "user_day/{hostel}",
            arguments = listOf(navArgument("hostel") { type = NavType.StringType })
        ) { backStackEntry ->
            val hostel = backStackEntry.arguments!!.getString("hostel")
            if (hostel != null) {
                PagerScreen(navController , hostel  )
            }
        }



        composable(
            route = "MessItem/{hostel}/{day}",
            arguments = listOf(
                navArgument("hostel") { type = NavType.StringType },
                navArgument("day") { type = NavType.StringType })
        ) { backStackEntry ->
            val hostel = backStackEntry.arguments!!.getString("hostel")
            val day = backStackEntry.arguments!!.getString("day")
            if (hostel != null && day != null) {
                 ItemsScreen(hostel , day , navController )
            }


        }

        composable(route = "notes_branch_screen") {
            NotesBranchScreen(navController)
        }

        composable(route ="Notes_Pyq_PinDocs") {
             Notes_Pyq_PinDocsScreen(navController)
        }

        composable(
            route = "notes_semester/{branch}",
            arguments = listOf(navArgument("branch") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            if (branch != null) {
                NotesSemesterScreen(navController, branch)
            }
        }

        composable(
            route =  "notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                 NotesScreen(navController, branch , semester)
            }


        }

        composable(route = "pyq_branch_screen") {
            PyqBranchScreen(navController )
        }

        composable(
            route = "pyq_semester/{branch}",
            arguments = listOf(navArgument("branch") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            if (branch != null) {
                PyqSemesterScreen(navController, branch)
            }
        }

        composable(
            route =  "pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PyqScreen(navController, branch , semester)
            }


        }

        composable(route = "pindocs_branch_screen") {
            PinDocsBranchScreen(navController )
        }

        composable(
            route = "pindocs_semester/{branch}",
            arguments = listOf(navArgument("branch") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            if (branch != null) {
                PinDocsSemesterScreen(navController, branch)
            }
        }

        composable(
            route =  "pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PinDocsScreen(navController, branch , semester)
            }


        }

        composable(route =  "cse_notes_screen/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
             CseNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "csenotes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                CseNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "cse_pyq_screen/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            CsePYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "csepyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                CsePyqScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "cse_pindocs_screen/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            CsePinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "cse_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                CsePinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "cse_faculty") {
             CseFacultyScreen(navController )
        }

        composable(route = "ece_faculty") {
            EceFacultyScreen(navController )
        }

        composable(route = "ece_semester_screen/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
             EceNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "ece_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                 EceNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "ece_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            EcePinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "ece_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                EcePinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "ece_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            EcePYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "ece_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                EcePYQScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "ee_faculty") {
            EeFacultyScreen(navController )
        }

//        composable(route = "ee_notes_semester") {
//            EeNotesSemesterScreen(navController)
//        }
        composable(route = "ee_notes_semester/{branch}") { backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            EeNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "ee_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                EeNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "ee_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            EePinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "ee_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                EePinDocsScreen(navController, branch , semester)
//            }
//
//
//        }


        composable(route = "ee_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            EePYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "ee_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                EePYQScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "eie_faculty") {
            EieFacultyScreen(navController )
        }

        composable(route = "eie_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            EieNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "eie_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                EieNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "eie_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            EiePinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "eie_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                EiePinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "eie_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            EiePYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "eie_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                EiePYQScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "math_faculty") {
            MathFacultyScreen(navController )
        }

        composable(route = "math_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            MathNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "math_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                MathNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "math_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            MathPinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "math_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                MathPinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "math_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            MathPYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "math_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                MathPYQScreen(navController, branch , semester)
//            }
//
//
//        }


        composable(route = "mech_faculty") {
            MechFacultyScreen(navController )
        }

        composable(route = "mech_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            MechNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "mech_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                MechNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "mech_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            MechPinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "mech_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                MechPinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "mech_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            MechPYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "mech_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                MechPYQScreen(navController, branch , semester)
//            }
//
//
//        }


        composable(route = "physics_faculty") {
            PhyFacultyScreen(navController )
        }

        composable(route = "physics_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            PhyNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "physics_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                PhyNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "physics_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            PhyPinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "physics_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                PhyPinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "physics_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            PhyPYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "physics_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                PhyPYQScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "pe_faculty") {
            PeFacultyScreen(navController )
        }

        composable(route = "pe_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            PeNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "pe_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                PeNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "pe_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            PePinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "pe_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                PePinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "pe_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            PePYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "pe_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                PePYQScreen(navController, branch , semester)
//            }
//
//
//        }


        composable(route = "bio_faculty") {
            BioFacultyScreen(navController )
        }

        composable(route = "bio_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            BioNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "bio_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                BioNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "bio_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            BioPinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "bio_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                BioPinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "bio_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            BioPYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "bio_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                BioPYQScreen(navController, branch , semester)
//            }
//
//
//        }


        composable(route = "chemical_faculty") {
            ChemicalFacultyScreen(navController )
        }

        composable(route = "chemical_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            if (branch != null) {
                ChemicalNotesPagerScreen(navController, branch)
            }
        }

//        composable(
//            route =  "chemical_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                ChemicalNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "chemical_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            ChemicalPinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "chemical_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                ChemicalPinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "chemical_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            ChemicalPYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "chemical_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                ChemicalPYQScreen(navController, branch , semester)
//            }
//
//
//        }


        composable(route = "chemistry_faculty") {
            ChemistryFacultyScreen(navController )
        }

        composable(route = "chemistry_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            ChemistryNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "chemistry_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                ChemistryNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "chemistry_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            ChemistryPinDocsPagerScreen(navController, branch)
        }

//        composable(
//                route =  "chemistry_pindocs/{branch}/{semester}",
//        arguments = listOf(
//            navArgument("branch") { type = NavType.StringType },
//            navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//        val branch = backStackEntry.arguments!!.getString("branch")
//        val semester = backStackEntry.arguments!!.getString("semester")
//        if (branch != null && semester!= null) {
//            ChemistryPinDocsScreen(navController, branch , semester)
//        }
//
//
//    }

        composable(route = "chemistry_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            ChemistryPYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "chemistry_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                ChemistryPYQScreen(navController, branch , semester)
//            }
//
//
//        }


        composable(route = "civil_faculty") {
            CivilFacultyScreen(navController )
        }

        composable(route = "civil_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            CivilNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "civil_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                CivilNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "civil_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            CivilPinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "civil_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                CivilPinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "civil_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            CivilPYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "civil_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                CivilPYQScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "calender") {
            CalenderUpdate(navController)
        }

        composable(route = "first_year") {
            FirstYear(navController)
        }

        composable(route = "administrators") {
            AdministratorsScreen(navController)
        }

        composable(route = "contactus") {
            ContactUs(navController)
        }

        composable(route = "1styear_notes_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            firstYearNotesPagerScreen(navController, branch)
        }

//        composable(
//            route =  "first_year_notes/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                FirstYearNotesScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "1styear_pindocs_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            firstYearPinDocsPagerScreen(navController, branch)
        }

//        composable(
//            route =  "first_year_pindocs/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                FirstYearPinDocsScreen(navController, branch , semester)
//            }
//
//
//        }

        composable(route = "1styear_pyq_semester/{branch}") {backStackEntry ->
            val branch = backStackEntry.arguments?.getString("branch") ?: ""
            firstYearPYQPagerScreen(navController, branch)
        }

//        composable(
//            route =  "first_year_pyq/{branch}/{semester}",
//            arguments = listOf(
//                navArgument("branch") { type = NavType.StringType },
//                navArgument("semester") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val branch = backStackEntry.arguments!!.getString("branch")
//            val semester = backStackEntry.arguments!!.getString("semester")
//            if (branch != null && semester!= null) {
//                FirstYearPYQScreen(navController, branch , semester)
//            }
//
//
//        }


    }
}

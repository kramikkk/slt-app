package com.example.signlanguagetranslator.composable

import com.example.signlanguagetranslator.R

data class SignEntry(
    val imageId: Int,
    val name: String,
    val detail: String,
    val additionalDetail: String
)

object DictionaryData {
    val entries = listOf(

        // ALPHABET
        SignEntry(
            imageId = R.drawable.lettera,
            name = "A",
            detail = "ASL for A",
            additionalDetail = "Form a fist with your thumb resting against your fingertips."
        ),
        SignEntry(
            imageId = R.drawable.letterb,
            name = "B",
            detail = "ASL for B",
            additionalDetail = "Extend your index, middle, and ring fingers while keeping your thumb tucked into your palm."
        ),
        SignEntry(
            imageId = R.drawable.letterc,
            name = "C",
            detail = "ASL for C",
            additionalDetail = "Make a claw shape with your hand by bending your fingers slightly."
        ),
        SignEntry(
            imageId = R.drawable.letterd,
            name = "D",
            detail = "ASL for D",
            additionalDetail = "Extend your index finger and thumb to form the shape of a 'D'."
        ),
        SignEntry(
            imageId = R.drawable.lettere,
            name = "E",
            detail = "ASL for E",
            additionalDetail = "Extend all fingers and thumb, keeping them together."
        ),
        SignEntry(
            imageId = R.drawable.letterf,
            name = "F",
            detail = "ASL for F",
            additionalDetail = "Extend your thumb, index, and middle fingers, keeping your ring and little fingers tucked into your palm."
        ),
        SignEntry(
            imageId = R.drawable.letterg,
            name = "G",
            detail = "ASL for G",
            additionalDetail = "Form a fist with your thumb resting against your index finger."
        ),
        SignEntry(
            imageId = R.drawable.letterh,
            name = "H",
            detail = "ASL for H",
            additionalDetail = "Form a fist with your thumb extended outward."
        ),
        SignEntry(
            imageId = R.drawable.letteri,
            name = "I",
            detail = "ASL for I",
            additionalDetail = "Point your index finger upward."
        ),
        SignEntry(
            imageId = R.drawable.letterj,
            name = "J",
            detail = "ASL for J",
            additionalDetail = "Form a hook shape with your index finger."
        ),
        SignEntry(
            imageId = R.drawable.letterk,
            name = "K",
            detail = "ASL for K",
            additionalDetail = "Extend your index and middle fingers, keeping the other fingers bent."
        ),
        SignEntry(
            imageId = R.drawable.letterl,
            name = "L",
            detail = "ASL for L",
            additionalDetail = "Extend your thumb, index, and middle fingers, keeping your ring and little fingers tucked into your palm."
        ),
        SignEntry(
            imageId = R.drawable.letterm,
            name = "M",
            detail = "ASL for M",
            additionalDetail = "Form a fist with your thumb extended, then turn your hand outward."
        ),
        SignEntry(
            imageId = R.drawable.lettern,
            name = "N",
            detail = "ASL for N",
            additionalDetail = "Form a claw shape with your index and middle fingers."
        ),
        SignEntry(
            imageId = R.drawable.lettero,
            name = "O",
            detail = "ASL for O",
            additionalDetail = "Form a circle with your thumb and index finger."
        ),
        SignEntry(
            imageId = R.drawable.letterp,
            name = "P",
            detail = "ASL for P",
            additionalDetail = "Extend your thumb, index, and middle fingers, keeping your ring and little fingers tucked into your palm."
        ),
        SignEntry(
            imageId = R.drawable.letterq,
            name = "Q",
            detail = "ASL for Q",
            additionalDetail = "Extend your thumb, index, and middle fingers, bending them slightly, and keep your ring and little fingers tucked into your palm."
        ),
        SignEntry(
            imageId = R.drawable.letterr,
            name = "R",
            detail = "ASL for R",
            additionalDetail = "Form a hook shape with your index finger, similar to 'J', but with your thumb extended."
        ),
        SignEntry(
            imageId = R.drawable.letters,
            name = "S",
            detail = "ASL for S",
            additionalDetail = "Hold your hand with your fingers slightly apart and palm facing downward, then move your hand in a waving motion."
        ),
        SignEntry(
            imageId = R.drawable.lettert,
            name = "T",
            detail = "ASL for T",
            additionalDetail = "Extend your index and middle fingers, keeping the other fingers bent."
        ),
        SignEntry(
            imageId = R.drawable.letteru,
            name = "U",
            detail = "ASL for U",
            additionalDetail = "Form a claw shape with your index, middle, and ring fingers."
        ),
        SignEntry(
            imageId = R.drawable.letterv,
            name = "V",
            detail = "ASL for V",
            additionalDetail = "Extend your index and middle fingers, separating them to form a 'V' shape."
        ),
        SignEntry(
            imageId = R.drawable.letterw,
            name = "W",
            detail = "ASL for W",
            additionalDetail = "Form a 'W' shape with your thumb, index, and middle fingers."
        ),
        SignEntry(
            imageId = R.drawable.letterx,
            name = "X",
            detail = "ASL for X",
            additionalDetail = "Cross your index and middle fingers to form an 'X' shape."
        ),
        SignEntry(
            imageId = R.drawable.lettery,
            name = "Y",
            detail = "ASL for Y",
            additionalDetail = "Extend your thumb, index, and middle fingers, and separate them to form a 'Y' shape."
        ),
        SignEntry(
            imageId = R.drawable.letterz,
            name = "Z",
            detail= "ASL for Z",
            additionalDetail = "Hold up your index finger and draw a 'Z' shape in the air."
        ),


        // WORDS
        SignEntry(
            imageId = R.drawable.afternoon,
            name = "Afternoon",
            detail = "ASL for Afternoon",
            additionalDetail = "To do the sign for \"afternoon\" just hold your dominant flat hand at a \"2 o'clock\" position pointing ahead and somewhat up."
        ),
        SignEntry(
            imageId = R.drawable.and,
            name = "And",
            detail = "ASL for And",
            additionalDetail = "The sign for \"and\" is very similar to the sign for \"leave\" (as in \"go away.\") The difference is that the sign \"leave\" (if you are right-handed) starts further to the right, ends much further to the right, and has a larger motion. The sign for AND only moves a few inches (unless you are being dramatic)."
        ),
        SignEntry(
            imageId = R.drawable.dosl,
            name = "Do",
            detail = "ASL for Do",
            additionalDetail = "To do this sign, turn your hands palm up in \"D\" handshapes and hold them in front of you with your index fingers pointing out/forward. Then keeping your hands in that position, spell the word \"DO\" twice."
        ),
        SignEntry(
            imageId = R.drawable.eat,
            name = "Eat",
            detail = "ASL for Eat",
            additionalDetail = "Make the sign for eat by taking your dominant hand, forming a flat ASL letter O sign, and tapping your fingers to your mouth once."
        ),
        SignEntry(
            imageId = R.drawable.evening,
            name = "Evening",
            detail = "ASL for Evening",
            additionalDetail = "The dominant, palm-down, flat hand dangles over the non-dominant, palm-down, flat hand, with the bottom of the dominant arm resting on top of the non-dominant hand."
        ),
        SignEntry(
            imageId = R.drawable.fine,
            name = "Fine",
            detail = "ASL for Fine",
            additionalDetail = "Bring one open hand up to the middle of your chest, with your thumb pointing towards you. Tap your thumb on your chest a couple of times."
        ),
        SignEntry(
            imageId = R.drawable.friend,
            name = "Friend",
            detail = "ASL for Friend",
            additionalDetail = "To make the sign for friend, hold out both of your index fingers hooked in a curved or 'C'-like shape. Holding one hand with your hook index facing up, hook the second index into the first. Then reverse the position for the hands and do it again. It is like your fingers are best friends and giving each other a hug."
        ),
        SignEntry(
            imageId = R.drawable.good,
            name = "Good",
            detail = "ASL for Good",
            additionalDetail = "Make the 2-handed sign for \"good\" by placing the fingers of your right hand against your lips. Move your right hand into the palm of your left hand. Both hands should be facing upward."
        ),
        SignEntry(
            imageId = R.drawable.hello,
            name = "Hello",
            detail = "ASL for Hello",
            additionalDetail = "To perform the sign “hello” in American Sign Language (ASL), simply place the hand you are writing with on your forehead close to your ear and move it outwards and away from your body. Don't forget to smile!"
        ),
        SignEntry(
            imageId = R.drawable.help,
            name = "Help",
            detail = "ASL for Help",
            additionalDetail = "Position your dominant hand (the one in a fist with the thumb up) above the non-dominant hand. Then move your dominant hand down toward the palm of your non-dominant hand and then lift it as if the non-dominant hand is \"helping\" the dominant hand up."
        ),
        SignEntry(
            imageId = R.drawable.how,
            name = "How",
            detail = "ASL for How",
            additionalDetail = "Place your hands together with the knuckles touching. (Looks kind of like McDonalds' Golden Arches.) Roll the hands forward until the \"arches\" are upside down--ending with your hands palm-up in \"cupping\" handshapes. When asking how something was done or how something happened, you should furrow your eyebrows."
        ),
        SignEntry(
            imageId = R.drawable.i,
            name = "I/Me",
            detail = "ASL for I/Me",
            additionalDetail = "You just point at yourself or touch your index finger to your chest. Often the concept of \"I\" or \"me\" is expressed by the beginning or ending location of the sign."
        ),
        SignEntry(
            imageId = R.drawable.iloveyou,
            name = "I Love You",
            detail = "ASL for I Love You",
            additionalDetail = "Showing a hand with a raised index finger and pinky (little) finger and an extended thumb."
        ),
        SignEntry(
            imageId = R.drawable.let,
            name = "Let",
            detail = "ASL for Let",
            additionalDetail = "Take both hands and form open, flat hands facing each other, all fingers held together on each hand, but at diagonal angles so that they aren't parallel."
        ),
        SignEntry(
            imageId = R.drawable.morning,
            name = "Morning",
            detail = "ASL for Morning",
            additionalDetail = "The sign for \"morning\" uses a palm-up flat hand (or slightly bent hand) on the dominant hand to represent the sun rising up from beneath the horizon. The non-dominant arm plays the role of the horizon. The wrist of the dominant hand contacts the non-dominant hand's fingers."
        ),
        SignEntry(
            imageId = R.drawable.my,
            name = "My",
            detail = "ASL for My",
            additionalDetail = "Form a flat hand and touch your chest twice."
        ),
        SignEntry(
            imageId = R.drawable.name,
            name = "Name",
            detail = "ASL for Name",
            additionalDetail = "To perform the sign “name” in American Sign Language (ASL) stick out your index and middle finger of both hands. Form a cross through moving the fingers of both hands together. Repeat the movement."
        ),
        SignEntry(
            imageId = R.drawable.need,
            name = "Need",
            detail = "ASL for Need",
            additionalDetail = "Starts as a straight index finger that turns into an \"X\" but the thumb is out as it moves toward the person being asked. Bends at the wrist up and down for twice."
        ),
        SignEntry(
            imageId = R.drawable.sorry,
            name = "Sorry",
            detail = "ASL for Sorry",
            additionalDetail = "The sign for \"sorry\" is made by forming an \"A' with your right hand. Rotate your hand on your chest using a couple of clockwise motions. This sign can also be used to mean \"apologize\" or \"regret.\"",
        ),
        SignEntry(
            imageId = R.drawable.welcome,
            name = "Welcome",
            detail = "ASL for Welcome",
            additionalDetail = "Holding the flat hand palm up out away from your body (off to the right a bit) and then bringing the hand in toward your torso."
        ),
        SignEntry(
            imageId = R.drawable.you,
            name = "You",
            detail = "ASL for You",
            additionalDetail = "Point with your finger at the person you are referring to."
        ),
        SignEntry(
            imageId = R.drawable.thanks,
            name = "Thanks",
            detail = "ASL for Thanks",
            additionalDetail = "In American Sign Language (ASL), expressing gratitude through the sign for \"thanks\" is a gesture that embodies appreciation and acknowledgment. To sign \"thanks,\" begin by raising your dominant hand, forming an open palm with your fingers slightly apart. Next, bring your hand up towards your chin, maintaining the open-palm shape with your fingers slightly curled. As you do this, you're symbolizing the act of receiving something. Then, with a gentle motion, move your hand forward and away from your chin in a small arc. This motion represents the expression of gratitude, extending your thanks outward."
        ),


    )
}
export function eventsInit() {
    return [
        {
            "id" : "EVENTID_event:0",
            "title" : "Wild animal spotted !",
            "description" : "You spot a wild animal in the distance. It has not yet noticed you. Would that be a good opportunity for some hunting ?",
            "eventCode" : "0",
            "options" : [
                {
                    "number" : "1",
                    "text" : "Let's grab our gear...",
                    "effectCode" : "i#ITEMID_item:1!3@ITEMID_item:4!-2",
                    "description" : "food +3, ammunition -2"
                },
                {
                    "number" : "2",
                    "text" : "No we don't have time for this.",
                    "effectCode" : "",
                    "description" : ""
                }
            ]
        }, 
        {
            "id" : "EVENTID_event:1",
            "title" : "Lucky findings on the road !",
            "description" : "You find some abandoned supplies alongside the road.",
            "eventCode" : "1",
            "options" : [
                {
                    "number" : "1",
                    "text" : "It's free real estate...",
                    "effectCode" : "i#ITEMID_item:1!3@ITEMID_item:0!3",
                    "description" : "food +3, water +3"
                }
            ]
        },
        {
            "id" : "EVENTID_event:2",
            "title" : "A traveler cut himself/herself with a knife.",
            "description" : "While playing with a knife, one of your travelers got hit with consequences.",
            "eventCode" : "2",
            "options" : [
                {
                    "number" : "1",
                    "text" : "Skill issue...",
                    "effectCode" : "c#x!stat!health!-15",
                    "description" : "target:x loses 15 health"
                }
            ]
        },
        {
            "id" : "EVENTID_event:3",
            "title" : "A traveler is sick.",
            "description" : "One of your travelers is now sick. Probably nothing to worry about.",
            "eventCode" : "3",
            "options" : [
                {
                    "number" : "1",
                    "text" : "Get better soon",
                    "effectCode" : "c#x!trait!add!TRAITID_trait:0",
                    "description" : "target:x gets TRAITID_trait:0"
                }
            ]
        },
        // {
        //     "id" : "EVENTID_event:4",
        //     "title" : "A nice fishing spot",
        //     "description" : "You come across a quiet pond. You wonder, could we stop for some fishing ?",
        //     "eventCode" : "4",
        //     "options" : [
        //         {
        //             "number" : "1",
        //             "text" : "Some fish would be a great addition to our diet.",
        //             "effectCode" : "i#ITEMID_item:1!5@ITEMID_item:13!-1",
        //             "description" : "food +5"
        //         },
        //         {
        //             "number" : "2",
        //             "text" : "No, let us just enjoy the view for a moment.",
        //             "effectCode" : "",
        //             "description" : ""
        //         }
        //     ]
        // },
        {
            "id" : "EVENTID_event:4",
            "title" : "Oh no ! Tuberculosis...",
            "description" : "One of your travelers has tuberculosis. He will not last very long...",
            "eventCode" : "4",
            "options" : [
                {
                    "number" : "1",
                    "text" : "Press F to pay respects I guess...",
                    "effectCode" : "c#x!trait!add!TRAITID_trait:3",
                    "description" : "target:x gets TRAITID_trait:3"
                }
            ]
        }
    ]
}
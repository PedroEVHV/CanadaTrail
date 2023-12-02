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
                    "effectCode" : "i#ITEMID_item:1!1",
                    "description" : "food +1"
                }
            ]
        },
        {
            "id" : "EVENTID_event:2",
            "title" : "target:0 cut himself/herself with a knife.",
            "description" : "While playing with a knife target:0 got hit with consequences.",
            "eventCode" : "2",
            "options" : [
                {
                    "number" : "1",
                    "text" : "Skill issue...",
                    "effectCode" : "c#0!stat!health!-15",
                    "description" : "target:0 loses 15 health"
                }
            ]
        }
    ]
}
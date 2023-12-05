export function gameSetup() {
    return {
        "id" : "SETUP0",
        "name" : "Default start",
        "travelers" : 4,
        "eventCap" : 5,
        "food" : {
            "amount" : 30,
            "item" : "ITEMID_item:1",
            "mult" : 10
        },
        "water" : {
            "amount" : 30,
            "item" : "ITEMID_item:0",
            "mult" : 10
        },
        "medical" : {
            "amount" : 30,
            "item" : "ITEMID_item:10",
            "mult" : 10
        },
        "assets" : {
            "main_menu_bg" : "interface_sprites/home.png",
            "map" : "interface_sprites/map.png",
        },
        "items" : [
            {
                "item" : "ITEMID_item:2",
                "amount" : 5
            },
            {
                "item" : "ITEMID_item:3",
                "amount" : 2
            },
            {
                "item" : "ITEMID_item:4",
                "amount" : 10
            },
            {
                "item" : "ITEMID_item:5",
                "amount" : 2
            },
            {
                "item" : "ITEMID_item:8",
                "amount" : 2
            }, 
            {
                "item" : "ITEMID_item:11",
                "amount" : 3
            },
            {
                "item" : "ITEMID_item:13",
                "amount" : 1
            },
            {
                "item" : "ITEMID_item:14",
                "amount" : 1
            }
        ]
    }
}
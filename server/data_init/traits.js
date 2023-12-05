export function traitsInit() {
    return [
        {
            "id" : "TRAITID_trait:0",
            "name" : "Light sickness",
            "description" : "Nothing to worry about, just don't let him work too much.",
            "duration" : 1,
            "effectCommand" : "c#x!stat!water!-1@x!stat!food!-1"
        },
        {
            "id" : "TRAITID_trait:1",
            "name" : "Smart",
            "description" : "This character is better suited for jobs that require some intellectual background.",
            "duration" : -1,
            "effectCommand" : ""
        },
        {
            "id" : "TRAITID_trait:2",
            "name" : "Meek",
            "description" : "This character never was the strong one in town. Don't trust him to carry you out of a difficult situation.",
            "duration" : -1,
            "effectCommand" : ""
        },
        {
            "id" : "TRAITID_trait:3",
            "name" : "Tuberculosis",
            "description" : "Deadly lung disease. This character might die soon.",
            "duration" : -1,
            "effectCommand" : "c#x!stat!water!-1@x!stat!health!-2"
        },
        {
            "id" : "TRAITID_trait:4",
            "name" : "Broken leg",
            "description" : "Good luck running with that...",
            "duration" : 3,
            "effectCommand" : "c#x!stat!health!-1@x!stat!water!-1@"
        }
    ]
}
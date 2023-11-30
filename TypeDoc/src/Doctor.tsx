/**
 * Get doctor list to show on homePage.
 */
export interface Doctor {
    /** Real name. */
    name: string;
    /** Doctor Department. */
    department: string;
    /** Phone number. */
    phone: string;
    /** The doctor's profession. */
    skill: string;
    /** Other information. */
    notes: string;
}


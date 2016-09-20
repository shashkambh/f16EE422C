/* Project2.cpp
 * Name: Shashank Kambhampati
 * EE312 Fall 2016
 * UTStrings
 */

#include <assert.h>
#include <string.h>
#include <stdlib.h>
#include "String.h"

/* use these two macros and the function functions if you want -- not required */
#define SIGNATURE (~0xdeadbeef)

/** STRING(s)
 * Helper macro for accessing ut String object (if applicable)
 * [INPUT] pointer to cstring
 * [OUTPUT] pointer to UT String object (if applicable)
 */
#define STRING(s) ((String*)(s - 3*sizeof(uint32_t)))

/* this simple function can be useful when you implement stage 4
 * you are not required to use the function, and you can implement stage 4 without it */

/* [NAME] isOurs
 * [Brief] Checks if a given string is part of a UTString
 * [In] s The string to be checked
 * [Returns] 1 if s is in a utstring, 0 otherwise
 * [Summary] Moves pointer back three locations and checks if signarture matches. Also
 *     checks to make sure that s is not null. */
int32_t isOurs(const char* s) 
{
    /*if (!isHeapVar(s)) { return 0; }*//*Removed: Michael Bartling: 06/21/2016*/
    if ((s != NULL) && (STRING(s)->check == SIGNATURE)) { return 1; }
    else { return 0; }
}

/* allocate a utstring on the heap, initialize the string correctly by copying
 * the characters from 'src' and return a pointer to the first character of actual string data. */

/* [NAME] utstrdup
 * [Brief] Converts a given string to a UTString
 * [In] src the string to be converted
 * [Returns] The converted copy of the string
 * [Summary] Calculates the length and keeps track of length an capacity in metadata. */
char* utstrdup(const char* src) 
{
    assert(src != NULL);
    
    /* Find length of src */
    unsigned int len = 0;
    while(src[len] != 0)
    {
        len++;
    }
    
    /* Allocate memory */
    String* newUtString = (String*)malloc(sizeof(String) + len + 1);

    /* Set properties and copy over data */
    newUtString->check = SIGNATURE;
    newUtString->capacity = len;
    utstrcpy(newUtString->data, src);

    return newUtString->data;
}

/* the parameter 'utstr' must be a utstring. Find the length of this string by accessing the meta-data
 * and return that length */

/* [NAME] utstrlen
 * [Brief] Returns the length of the given UTString
 * [In] The UTString to get the length of
 * [Returns] The length of the given UTString
 * [Summary] Accesses metadata to get the length of the given UTString. */
uint32_t utstrlen(const char* utstr) 
{
    /* Accesses metadata to get length. If metadata not present, returns -1. */
    assert(isOurs(utstr));

    String* strWithData = STRING(utstr);

    return strWithData->length;
}

/* s must be a utstring. suffix can be an ordinary string (or a utstring)
 * append the characters from suffix to the string s. Do not allocate any additional storage, and
 * only append as many characters as will actually fit in s. Update the length meta-data for utstring s
 * and then return s */

/* [NAME] utstrcat
 * [Brief] Concatenates a given string onto a given UTString
 * [In] s The UTString that will be added to
 * [In] suffix The source of the characters to add
 * [Returns] A pointer to the UTString data after concatenation
 * [Summary] Concatenates suffix onto s, but only goes to the capacity of s. 
 *     If suffix cannot fit in s, then extra characters will be omitted. */
char* utstrcat(char* s, const char* suffix) 
{
    assert(isOurs(s));

    String* strWithData = STRING(s);
    unsigned int numCharsAdded = 0;
    unsigned int suffixLength = 0;

    /* Find length of suffix */
    while(suffix[suffixLength] != 0)
    {
        suffixLength++;
    }

    /* While not at end of suffix and still have more room, add a character */
    while((numCharsAdded < suffixLength) && (strWithData->length < strWithData->capacity))
    {
        strWithData->data[strWithData->length] = suffix[numCharsAdded];
        numCharsAdded++;
        strWithData->length++;
    }
    strWithData->data[strWithData->length] = '\0';

    return strWithData->data;
}

/* 'dst' must be a utstring. 'src' can be an ordinary string (or a utstring)
 * overwrite the characters in dst with the characters from src. Do not overflow the capacity of dst
 * For example, if src has five characters and dst has capacity for 10, then copy all five characters
 * However, if src has ten characters and dst only has capacity for 8, then copy only the first 8
 * characters. Do not allocate any additional storage, do not change capacity. Update the length
 * meta-data for dst and then return dst */

/* [NAME] utstrcpy
 * [Brief] Copies from a string into a UTString
 * [In] dst The UTString to be copied into
 * [In] src The string to be copied from
 * [Returns] A pointer to the UTString with new data
 * [Summary] Has similar behaviour to utstrcat. Copies as many characters as will fit from src
 * into dst. */
char* utstrcpy(char* dst, const char* src) 
{
    assert(isOurs(dst));
    assert(src != NULL);
 
    char* stringOut_r = dst;
    String* strWithData = STRING(dst);
   
    /* Make length 0, then call cat. Copies the string, since cat adds from index 0. */
    strWithData->length = 0;
    stringOut_r = utstrcat(dst, src);

    return stringOut_r;
}

/* self must be a utstring. deallocate the storage for this string
 * (i.e., locate the start of the chunk and call free to dispose of the chunk, note that the start of
 * the chunk will be 12 bytes before *self) */

/* [NAME] utstrfree
 * [Brief] Frees a given UTString
 * [In] The UTString to be freed
 * [Summary] Frees a UTString along with its metadata. */
void utstrfree(char* self) 
{
    assert(isOurs(self));

    /* If self is a utstring, free the entire struct */
    String* strWithData = STRING(self);
    free(strWithData);
}

/* s must be a utstring.
 * ensure that s has capacity at least as large as 'new_capacity'
 * if s already has capacity equal to or larger than new_capacity, then return s
 * if s has less capacity than new_capacity, then allocate new storage with sufficient space to store
 * new_capacity characters (plus a terminating zero), copy the current characters from s into this
 * new storage. Update the meta-data to correctly describe new new utstring you've created, deallocate s
 * and then return a pointer to the first character in the newly allocated storage */

/* [NAME] utstrrealloc
 * [Brief] Ensures a given capacity for a given UTString
 * [In] s The UTString to check
 * [In] new_capacity The capacity that s needs to be
 * [Returns] A pointer to the data of the UTString
 * [Summary] If s has less capacity than new_capacity, it is reallocated. 
 * Otherwise it is left alone. */
char* utstrrealloc(char* s, uint32_t new_capacity) 
{
    assert(isOurs(s));

    String* utString=STRING(s);

    /* If s is a utrstring and has less space than new_capacity, then reallocate and update capacity */
    if(utString->capacity < new_capacity)
    {
        utString=(String*)realloc(utString, sizeof(String) + new_capacity + 1);
        utString->capacity=new_capacity;
    } 

    return utString->data;
}



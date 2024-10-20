#include "xparameters.h"
#include "xgpio.h"
#include "xil_printf.h"
#include <stdio.h>
#include <stdint.h>
#include <xil_types.h>
#include <time.h>  

void insertionSort(uint8_t arr[], uint8_t length) {
    for (int i = 1; i < length; i++) {
        int key = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

#define MAX_SIZE 16
#define MAX_INPUT_SIZE 256

int main(void) {
    char input_str[MAX_INPUT_SIZE];
    uint8_t array[MAX_SIZE];  
    uint8_t array_size = 0;

    while(TRUE) {
        memset(input_str, 0, MAX_INPUT_SIZE); 
        xil_printf("Enter numbers separated by spaces and press Enter: ");
        char* p = input_str;
        char c;
        while ((c = inbyte()) != '\r') {
            if (p - input_str < MAX_INPUT_SIZE - 1) {
                *p++ = c;
                outbyte(c);
            }
        }
        xil_printf("\n\r");

        char* token = strtok(input_str, " ");
        array_size = 0;

        while (token != NULL && array_size < MAX_SIZE) {
            array[array_size++] = (uint8_t)atoi(token);
            token = strtok(NULL, " ");
        }

        // Start timing
        clock_t start_time = clock();

        insertionSort(array, array_size);

        // End timing
        clock_t end_time = clock();
        double time_taken = ((double)(end_time - start_time)) / CLOCKS_PER_SEC * 1000000000; 
        //  nanoseconds



        xil_printf("Sorting completed in %.0f nanoseconds\n\r", time_taken);

        xil_printf("Sorted output: ");
        for (int i = 0; i < array_size; i++) {
            xil_printf("%d ", array[i]);
        }
        xil_printf("\n\r");
    }
}



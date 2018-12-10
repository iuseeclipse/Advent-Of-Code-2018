clear
close all
clc

load input.txt -ascii

freq = 0;
for i = 1:length(input)
    freq = freq + input(i);
end
disp(freq);


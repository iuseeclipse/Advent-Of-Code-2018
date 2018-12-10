clear
close all
clc

load input.txt -ascii

freq = [0];
done = false;
while(~done)
    for i = 1:length(input)
        freq = [freq, freq(length(freq)) + input(i)];
        if(length(freq) ~= length(unique(freq)))
            disp(freq(length(freq)));
            done = true;
            break;
        end
    end
end


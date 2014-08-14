function weight = Phi1(drag, g, v)
%PHI1 Normal membership function with weighted signature (interface).
%Memebrship fucntion defined as:
%
% where
% * drag is an ontology DRAG struct. See newDrag() for details.
% * g - is a gene of interest
% * v - is a vertice or a classification from drag
%
%Function returns weight \in \Z^+ (uint8).

import endewem.*

geneIndex = indexOfGene(drag, g);
verticeIndex = indexOfVertice(drag, v);
m = [verticeIndex geneIndex];

isFound = false;
[numOfRows numOfColumns] = size(drag.A);
for row = 1:numOfRows
    if isempty(find(drag.A(row, :) ~= m))
        isFound = true;
        break
    end
end

weight = uint8(isFound);

end

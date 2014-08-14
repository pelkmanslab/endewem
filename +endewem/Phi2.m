function weight = Phi2(drag, g, v)
%PHI2 A number of paths in the ontology DRAG from v to any v' to which g 
% is mapped.
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

% Set of the all depended verticies (down the DRAG), a power of which 
% equals to the number of dependent paths plus non-annotatating verticies.
dependedVertices = getDependedVerticies(drag.E, [verticeIndex]);

% Substract non-annotating verticies.
dependedMapRows = find(ismember(drag.A(:,1), dependedVertices));
weight = uint8(sum(ismember(drag.A(dependedMapRows,2), geneIndex)));

end

function dependedVertices = getDependedVerticies(edges, vertices)    
    indexes = find(ismember(edges(:, 1), vertices));
    downIndexes = edges(indexes, 2);
    tailIndexes = [];
    if ~isempty(downIndexes)
        tailIndexes = getDependedVerticies(edges, downIndexes);
    end
    dependedVertices = vertcat(downIndexes, tailIndexes);
end
function tests = ProbTest()
    tests = functiontests(localfunctions);
end


function drag = buildExampleDrag()
import endewem.*;

drag = newDrag();
drag.V = {'r', 'v1', 'v2', 'v3', 'v4', 'v5', 'v6', 'v7', 'v8', 'v9', 'v10'};
drag.r = 1;
drag = appendArc(drag, 'r', 'v1');
drag = appendArc(drag, 'r', 'v2');
drag = appendArc(drag, 'r', 'v3');
drag = appendArc(drag, 'r', 'v4');
drag = appendArc(drag, 'v1', 'v5');
drag = appendArc(drag, 'v2', 'v9');
drag = appendArc(drag, 'v3', 'v6');
drag = appendArc(drag, 'v6', 'v9');
drag = appendArc(drag, 'v4', 'v7');
drag = appendArc(drag, 'v4', 'v8');
drag = appendArc(drag, 'v8', 'v10');

drag.G = {'g1', 'g2', 'g3', 'g4', 'g5', 'g6', 'g7', 'g8'};
drag = map(drag, 'v1', 'g3');
drag = map(drag, 'v1', 'g4');
drag = map(drag, 'v1', 'g8');
drag = map(drag, 'v2', 'g1');
drag = map(drag, 'v2', 'g6');
drag = map(drag, 'v5', 'g3');
drag = map(drag, 'v5', 'g6');
drag = map(drag, 'v5', 'g8');
drag = map(drag, 'v5', 'g4');
drag = map(drag, 'v6', 'g5');
drag = map(drag, 'v9', 'g4');
drag = map(drag, 'v9', 'g8');
drag = map(drag, 'v7', 'g7');
drag = map(drag, 'v7', 'g2');
drag = map(drag, 'v7', 'g1');
drag = map(drag, 'v8', 'g2');
drag = map(drag, 'v8', 'g7');
drag = map(drag, 'v10', 'g3');
drag = map(drag, 'v10', 'g7');

end


function testProb(testCase)
import endewem.*;

drag = buildExampleDrag();
drag = map(drag, 'v2', 'g3');
drag.X = [1 3 4 1]; % some arbitrary gene expression levels

H = find(ismember(drag.G, {'g2' 'g3'}));
pValue = Prob(drag, @Phi3, @Phi1, H, 'v2');

end
